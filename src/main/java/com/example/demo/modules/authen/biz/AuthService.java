package com.example.demo.modules.authen.biz;

import com.example.demo.modules.authen.model.AuthenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AuthenRepository authRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AuthenEntity authEntity = authRepo.findByEmail(userName);
        if (authEntity == null) {
            throw new UsernameNotFoundException("User " + userName + " not found!");
        }

        UserDetails userDetails = (UserDetails) new User(authEntity.getEmail(), authEntity.getPassword(), null);
        return userDetails;
    }
}
