package com.adyt.anima.security.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    @Email
    @JsonProperty("email")
    private String email;

    @Size(min = 8)
    @JsonProperty("password")
    private String password;

    @Size(min = 3, max = 64)
    @JsonProperty("name")
    private String name;

    public UserJpa toJpa(){
        return new UserJpa().email(email).password(password).name(name);
    }

}
