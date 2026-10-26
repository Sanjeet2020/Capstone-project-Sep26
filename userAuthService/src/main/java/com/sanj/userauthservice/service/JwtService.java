package com.sanj.userauthservice.service;

import com.sanj.userauthservice.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "your-secret-key"; // Replace with your actual secret key
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    public String generateToken(User user) {
        // Implement JWT token generation logic here
        Jwts.builder().subject(user.getEmail())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .issuer("userAuthService").issuedAt(new Date());
        // For simplicity, we will return a dummy token
        return "dummy-jwt-token-for-user-";
    }
}
