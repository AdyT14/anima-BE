package com.adyt.anima.security.login;

import com.adyt.anima.security.exceptions.InvalidEmailException;
import com.adyt.anima.security.user.UserDTO;
import com.adyt.anima.security.user.UserJpa;
import com.adyt.anima.security.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoginService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public LoginService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerNewUser(UserDTO userDTO) {

        var user = userRepository.findDistinctByEmail(userDTO.getEmail());
        if (user.isPresent()) {
            throw new InvalidEmailException();
        }
        var userJpa = new UserJpa()
                .email(userDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .name(userDTO.getName());
        userRepository.save(userJpa);
    }

}
