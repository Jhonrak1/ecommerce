package com.suarez.infrastructure.repo.service;

import com.suarez.domain.model.User;
import com.suarez.domain.service.UserService;
import com.suarez.infrastructure.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    //implements UserService
//    private final UserRepo userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public User createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    @Override
//    public Optional<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    @Override
//    public User findById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//    }
}

