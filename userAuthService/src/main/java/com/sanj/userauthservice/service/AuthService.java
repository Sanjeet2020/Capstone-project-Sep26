package com.sanj.userauthservice.service;

import com.sanj.userauthservice.dto.AuthRequest;
import com.sanj.userauthservice.model.Role;
import com.sanj.userauthservice.model.User;
import com.sanj.userauthservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User signup(AuthRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            return null;
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}
