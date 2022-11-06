package com.example.demo.modules.authen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AuthRes {
    private String email;
    private String accessToken;
}
