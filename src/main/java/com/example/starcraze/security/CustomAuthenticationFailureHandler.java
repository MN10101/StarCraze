package com.example.starcraze.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("Login failed: " + exception.getClass().getSimpleName() + " - " + exception.getMessage());  // Log the failure
        if (exception instanceof BadCredentialsException) {
            System.out.println("Invalid username or password.");
        } else if (exception instanceof AccountStatusException) {
            System.out.println("Account is disabled or locked.");
        }
        response.sendRedirect("/horoscope-log?error=true");  // Redirect to the login page with an error message
    }

}


