package com.example.starcraze;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncoderTest {

    @Test
    public void testPasswordEncodingWithUsername() {
        // Initialize the encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Username and password
        String username = "user2";
        String rawPassword = "Mamoton017#";

        // Encode the password
        String hashedPassword = encoder.encode(rawPassword);

        // Print out the username and hashed password (for manual inspection)
        System.out.println("Username: " + username);
        System.out.println("Hashed Password: " + hashedPassword);

        // Check if the hashed password matches the raw password
        assertTrue(encoder.matches(rawPassword, hashedPassword));
    }
}
