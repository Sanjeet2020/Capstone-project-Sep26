package com.sanj.userauthservice.controller;

import com.sanj.userauthservice.dto.AuthRequest;
import com.sanj.userauthservice.dto.UserDto;
import com.sanj.userauthservice.model.User;
import com.sanj.userauthservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody AuthRequest request) {
        User user = authService.signup(request);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
