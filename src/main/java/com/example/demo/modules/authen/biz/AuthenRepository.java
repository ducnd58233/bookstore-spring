package com.example.demo.modules.authen.biz;

import com.example.demo.modules.authen.model.AuthenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenRepository extends JpaRepository<AuthenEntity, Integer> {
    AuthenEntity findByEmail(String email);
}
