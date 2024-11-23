package com.example.starcraze.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, message = "Username must be at least 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=]).{8,}$",
            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character")
    private String password;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private boolean enabled;

    // One-to-Many relationship, with cascading and orphan removal
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Authority> authorities = new ArrayList<>();


    public User() {}

    public User(String username, String password, String email, boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    public void addAuthority(Authority authority) {
        authority.setUser(this);  // Link authority to user
        this.authorities.add(authority);  // Add authority to the list
    }

    public String getRole() {
        if (!authorities.isEmpty()) {
            return authorities.get(0).getAuthority();  // Return the first authority as the user's role
        }
        return null;  // No role available
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
