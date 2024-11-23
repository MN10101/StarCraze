package com.example.starcraze.controller;

import com.example.starcraze.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/reset-password-request")
    public String handlePasswordResetRequest(@RequestParam String email, Model model) {
        boolean isEmailSent = passwordService.sendPasswordResetEmail(email);
        if (isEmailSent) {
            model.addAttribute("message", "Password reset link sent to your email!");
        } else {
            model.addAttribute("error", "No account found with that email address.");
        }
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        if (passwordService.isTokenValid(token)) {
            model.addAttribute("token", token);
            return "reset-password"; // Renders the reset password form
        } else {
            model.addAttribute("error", "Invalid or expired reset token.");
            return "reset-password";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String password, Model model) {
        boolean isResetSuccessful = passwordService.resetPassword(token, password);
        if (isResetSuccessful) {
            model.addAttribute("message", "Your password has been successfully reset.");
            return "login"; // After resetting password, show the login page
        } else {
            model.addAttribute("error", "Invalid or expired reset token.");
            return "reset-password";
        }
    }
}
