package com.example.starcraze.controller;

import com.example.starcraze.entity.User;
import com.example.starcraze.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/horoscope-reg")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/horoscope-register-post")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        // Save user after encoding the password
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(username);
        newUser.setEnabled(true);
        userService.save(newUser);

        model.addAttribute("message", "Registration successful!");
        return "login";
    }
}
