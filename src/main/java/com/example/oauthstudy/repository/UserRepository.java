package com.example.oauthstudy.repository;

import com.example.oauthstudy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);
}

// localhost:8080/logout/aouth  <- 로그아웃!