package com.suarez.presentation.controller;

import com.suarez.domain.model.User;
import com.suarez.domain.service.AuthenticationService;
import com.suarez.presentation.controller.dto.AuthResponseDto;
import com.suarez.presentation.controller.dto.LoginRequestDto;
import com.suarez.presentation.controller.dto.UserRequestDto;
import com.suarez.presentation.controller.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API")
public class AuthController {
//    private final AuthenticationService authenticationService;
//
//    @PostMapping("/register")
//    @Operation(summary = "Register a new user")
//    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userDto) {
//        User user = User.builder()
//                .username(userDto.getUsername())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .build();
//
//        User savedUser = authenticationService.register(user);
//
//        UserResponseDto response = UserResponseDto.builder()
//                .id(savedUser.getId())
//                .username(savedUser.getUsername())
//                .email(savedUser.getEmail())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @PostMapping("/login")
//    @Operation(summary = "Authenticate user and get token")
//    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginDto) {
//        String token = authenticationService.login(loginDto.getUsername(), loginDto.getPassword());
//
//        AuthResponseDto response = AuthResponseDto.builder()
//                .token(token)
//                .username(loginDto.getUsername())
//                .build();
//
//        return ResponseEntity.ok(response);
//    }
}
