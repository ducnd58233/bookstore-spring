package com.example.demo.modules.authen.controller;

import com.example.demo.components.tokenprovider.JwtTokenProvider;
import com.example.demo.modules.authen.biz.AuthenRepository;
import com.example.demo.modules.authen.model.AuthRes;
import com.example.demo.modules.authen.model.AuthenEntity;
import com.example.demo.modules.authen.model.AuthenReq;
import com.example.demo.modules.user.biz.UserRepository;
import com.example.demo.modules.user.model.UserEntity;
import com.example.demo.modules.user.model.UserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


import javax.validation.Valid;

import static com.example.demo.common.Constants.ROLE_USER;

@RestController
@RequestMapping(value="/auth")
public class AuthenController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthenRepository authRepo;

    @PostMapping(value="/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserReq req) {
        if (authRepo.findByEmail(req.getEmail()) != null) return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists!");

        String hashedPassword = new BCryptPasswordEncoder().encode(req.getPassword());

        UserEntity user = new UserEntity();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPhone(req.getPhone());
        userRepo.save(user);

        AuthenEntity auth = new AuthenEntity();
        auth.setEmail(req.getEmail());
        auth.setPassword(hashedPassword);
        auth.setRole(ROLE_USER);
        authRepo.save(auth);

        return ResponseEntity.status(HttpStatus.CREATED).body("Account created successful!");
    }

    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenReq req) {
        try {
            Authentication authenticaion = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );
            AuthenEntity user = (AuthenEntity) authenticaion.getPrincipal();
            String accessToken = new JwtTokenProvider().generateToken(user);

            AuthRes response = new AuthRes(user.getEmail(), accessToken);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or Password is incorrect!");
        }
    }
}
