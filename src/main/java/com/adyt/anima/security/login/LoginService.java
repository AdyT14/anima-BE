package com.adyt.anima.security.login;

import com.adyt.anima.security.user.UserDTO;
import com.adyt.anima.security.user.UserJpa;
import com.adyt.anima.security.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    public LoginService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public void registerNewUser(UserDTO userDTO) {
        var userJpa = new UserJpa()
                .email(userDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .name(userDTO.getName());
        userRepository.save(userJpa);
    }

}
