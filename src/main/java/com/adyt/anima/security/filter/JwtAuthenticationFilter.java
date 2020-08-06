package com.adyt.anima.security.filter;

import com.adyt.anima.security.SecurityConfiguration;
import com.adyt.anima.security.exceptions.FailedToObtainUserCredentialsException;
import com.adyt.anima.security.user.UserDTO;
import com.adyt.anima.security.user.UserJpa;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final Long EXPIRATION_TIME = 300000L * 60;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfiguration securityConfiguration;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, SecurityConfiguration securityConfiguration) {
        this.authenticationManager = authenticationManager;
        this.securityConfiguration = securityConfiguration;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            var userJpa = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class).toJpa();

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userJpa.email(), userJpa.password(), new ArrayList<>()));

        } catch (IOException e) {
            throw new FailedToObtainUserCredentialsException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        var token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(securityConfiguration.getSecretKey()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
