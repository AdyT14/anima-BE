package com.adyt.anima.security.login;

import com.adyt.anima.security.exceptions.InvalidEmailException;
import com.adyt.anima.security.user.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserDTO userDTO) {
        try {
            loginService.registerNewUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (InvalidEmailException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
