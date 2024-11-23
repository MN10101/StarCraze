package com.example.starcraze.services;

import com.example.starcraze.entity.PasswordResetToken;
import com.example.starcraze.entity.User;
import com.example.starcraze.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordService {
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public PasswordService(JavaMailSender mailSender,
                           UserService userService,
                           PasswordEncoder passwordEncoder,
                           PasswordResetTokenRepository passwordResetTokenRepository) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Value("${app.domain}")
    private String appDomain;

    public boolean sendPasswordResetEmail(String email) {
        Optional<User> userOptional = userService.findByEmail(email);  // Find user by email

        if (userOptional.isPresent()) {
            User user = userOptional.get();  // Unwrap the Optional to get the User object
            String resetToken = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);

            // Save the reset token to the database
            PasswordResetToken tokenEntity = new PasswordResetToken(resetToken, expiryDate, user);
            passwordResetTokenRepository.save(tokenEntity);

            String resetLink = appDomain + "/reset-password?token=" + resetToken;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, click the link below:\n" + resetLink);

            try {
                mailSender.send(message);
                return true;
            } catch (MailException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            // User was not found
            return false;
        }
    }


    public boolean isTokenValid(String token) {
        if (token == null || token.isEmpty()) {
            System.out.println("Token is empty or null.");
            return false;
        }

        Optional<PasswordResetToken> resetToken = passwordResetTokenRepository.findByToken(token);
        if (resetToken.isPresent()) {
            PasswordResetToken tokenEntity = resetToken.get();
            boolean isNotExpired = tokenEntity.getExpiryDate().isAfter(LocalDateTime.now());
            System.out.println("Token expiry check: " + isNotExpired);
            return isNotExpired;
        }

        System.out.println("Token not found.");
        return false;
    }


    public boolean resetPassword(String token, String newPassword) {
        if (token == null || token.isEmpty()) {
            return false;
        }

        Optional<PasswordResetToken> resetTokenOpt = passwordResetTokenRepository.findByToken(token);
        if (!resetTokenOpt.isPresent()) {
            return false;
        }

        PasswordResetToken resetToken = resetTokenOpt.get();
        User user = resetToken.getUser();

        if (user == null) {
            return false;
        }

        // Log the new password before encoding
        System.out.println("New password before encoding: " + newPassword);

        // Encode the new password and update the user's password
        String encodedPassword = passwordEncoder.encode(newPassword);
        System.out.println("Encoded password: " + encodedPassword);

        user.setPassword(encodedPassword);

        // Save the updated user
        userService.update(user);
        return true;
    }



}
