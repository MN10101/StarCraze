package com.example.starcraze.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendWelcomeEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Star Craze!");
        message.setText("Dear user,\n\nWelcome to Star Craze! We're thrilled to have you with us.\n\nBest regards,\nStar Craze Team");
        emailSender.send(message);
    }
}
