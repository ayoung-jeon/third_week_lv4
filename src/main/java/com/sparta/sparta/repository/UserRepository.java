package com.sparta.sparta.repository;

import com.sparta.sparta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 회원 중복 확인을 위해 -> findByUsername
    Optional<User> findByUsername(String username);
    // email 중복확인
    Optional<User> findByEmail(String email);
}
