package com.example.starcraze.controller;

import com.example.starcraze.entity.User;
import com.example.starcraze.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HoroscopeController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public HoroscopeController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Display registration page
    @GetMapping("/horoscope-register")
    public String registerPage() {
        return "register";
    }

    // Handle registration form submission
    @PostMapping("/horoscope-register")
    public String registerUser(@RequestParam String email,
                               @RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if (email == null || username == null || password == null) {
            model.addAttribute("error", "All fields are required!");
            return "register";
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));

        try {
            userService.save(newUser);
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }

        return "redirect:/horoscope-log";
    }

    // Display login page
    @GetMapping("/horoscope-log")
    public String showLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    // Display main page after successful login
    @GetMapping("/mn")
    public String mnPage() {
        return "mn";
    }

    @GetMapping("/ca")
    public String zodiacPage() {
        return "zodiac";
    }

    @GetMapping("/privacy-policy")
    public String privacyPolicyPage() {
        return "privacy-policy";
    }

    @GetMapping("/terms-and-conditions")
    public String termsAndConditionsPage() {
        return "terms-and-conditions";
    }

    @GetMapping("/latest-blogs")
    public String latestBlogsPage() {
        return "latest-blogs";
    }



}

