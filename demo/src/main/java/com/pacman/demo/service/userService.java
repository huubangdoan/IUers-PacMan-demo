package com.pacman.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pacman.demo.entity.user;
import com.pacman.demo.repository.userRepository;

@Service
public class userService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public user register(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }
        user user = new user();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword)); 
        return userRepository.save(user);
    }

    public Optional<user> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}