package com.example.demo.modules.authen.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter @Setter
public class AuthenReq {
    @Email
    @Length(min=5, max=100)
    private String email;
    @Length(min=5, max=12)
    private String password;
}
