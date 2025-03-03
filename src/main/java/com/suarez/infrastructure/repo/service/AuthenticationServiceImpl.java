package com.suarez.infrastructure.repo.service;

import com.suarez.domain.model.User;
import com.suarez.domain.service.AuthenticationService;
import com.suarez.infrastructure.repo.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

//@Service
//@RequiredArgsConstructor
public class AuthenticationServiceImpl  {
//    private final AuthenticationManager authenticationManager;
//    private final UserRepo userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    @Value("${jwt.expiration}")
//    private long jwtExpiration;
//
//    @Override
//    public String login(String username, String password) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//
//        if (authentication.isAuthenticated()) {
//            return generateToken(username);
//        }
//
//        throw new RuntimeException("Invalid username or password");
//    }
//
//    @Override
//    public User register(User user) {
//        // Check if username or email already exists
//        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//            throw new RuntimeException("Username already exists");
//        }
//
//        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already exists");
//        }
//
//        // Encrypt password
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        return userRepository.save(user);
//    }
//
//    private String generateToken(String username) {
//        byte[] signingKey = jwtSecret.getBytes();
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
//                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
//                .compact();
//    }
}
