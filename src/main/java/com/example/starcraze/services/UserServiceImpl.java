package com.example.starcraze.services;

import com.example.starcraze.entity.Authority;
import com.example.starcraze.entity.User;
import com.example.starcraze.repository.UserRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.emailService = emailService;
    }

        // CRUD OPERATIONS

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found."));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username " + username + " not found."));
    }

    @Override
    @Transactional
    public void save(User user) {
        if (user.getId() == null) {
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                throw new IllegalArgumentException("Email already exists: " + user.getEmail());
            }

            user.setEnabled(true);
            userRepository.save(user);

            Authority authority = new Authority("ROLE_USER", user);
            authorityService.save(authority);
            user.addAuthority(authority);

            userRepository.save(user);

            // Send the welcome email
            emailService.sendWelcomeEmail(user.getEmail());
        } else {
            userRepository.save(user);
        }
    }


    @Override
    public void update(User user) {
        System.out.println("Updating user with username: " + user.getUsername());
        userRepository.save(user);
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Collection<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
