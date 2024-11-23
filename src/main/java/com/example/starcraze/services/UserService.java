package com.example.starcraze.services;

import com.example.starcraze.entity.Authority;
import com.example.starcraze.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    void save(User user);
    void update(User user);
    void deleteById(Long id);
    Optional<User> findByEmail(String email);

}
