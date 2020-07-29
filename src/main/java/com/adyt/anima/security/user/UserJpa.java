package com.adyt.anima.security.user;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Accessors(chain = true, fluent = true)
@Table(name = "users")
public class UserJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "This is not a valid email")
    private String email;

    @Size(min = 8)
    private String password;

    @Size(min = 2, max = 64)
    private String name;

}
