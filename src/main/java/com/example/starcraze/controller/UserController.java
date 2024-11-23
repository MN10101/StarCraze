//package com.example.starcraze.controller;
//
//import com.example.starcraze.entity.Authority;
//import com.example.starcraze.entity.User;
//import com.example.starcraze.services.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//
//@Controller
//@RequestMapping("/api/auth")
//public class UserController {
//
//    private final UserServiceImpl userService;
//
//
//    @Autowired
//    public UserController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    // CREATE
//    @PostMapping("/register")
//    public String registerUser(@Valid @RequestBody User user, BindingResult result) {
//        if (result.hasErrors()) {
//            return "register";
//        }
//
//        try {
//            userService.save(user);
//            return "redirect:/login";
//        } catch (IllegalArgumentException e) {
//            return "redirect:/register?error=" + e.getMessage();
//        }
//    }
//
//    // READ
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    // UPDATE
//    @PutMapping("/update")
//    public String updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "update";
//        }
//        userService.save(user);
//        return "redirect:/login";
//    }
//
//    // DELETE
//    @DeleteMapping("/delete")
//    public String deleteUser(@Valid @RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "delete";
//        }
//        userService.deleteById(user.getId()); // Ensure user.getId() provides a valid ID
//        return "redirect:/login";
//    }
//
//}