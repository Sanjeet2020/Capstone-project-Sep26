package com.sanj.userauthservice.controller;

import com.sanj.userauthservice.dto.UserDto;
import com.sanj.userauthservice.model.User;
import com.sanj.userauthservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Authentication authentication) {
        Optional<User> userOptional = userRepository.findByEmail(authentication.getName());
        if(userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
     UserDto dto = new UserDto();
        dto.setId(userOptional.get().getId());
        dto.setEmail(userOptional.get().getEmail());
        dto.setRole(userOptional.get().getRole());
        return ResponseEntity.ok(dto);
    }
}
