package com.example.demo.modules.user.biz;

import com.example.demo.modules.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findOneByEmail(String email);
}
