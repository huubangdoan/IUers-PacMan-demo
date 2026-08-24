package com.pacman.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pacman.demo.entity.user;

public interface userRepository extends JpaRepository<user, Integer> {
    Optional<user> findByEmail(String email);
    Optional<user> findByUsername(String username);
    boolean existsByEmail(String email);
}