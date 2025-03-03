package com.suarez.domain.service;

import com.suarez.domain.model.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> findByUsername(String username);
    User findById(Long id);
}
