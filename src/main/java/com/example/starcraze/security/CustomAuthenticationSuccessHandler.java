package com.example.starcraze.security;

import com.example.starcraze.entity.User;
import com.example.starcraze.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("Login success for user: " + userDetails.getUsername());

        User user = userService.findByUsername(userDetails.getUsername());

        if (!user.isEnabled()) {
            request.getSession().invalidate();
            response.sendRedirect("/horoscope-log?blocked=true");  // Blocked user redirects to custom login page
        } else {
            response.sendRedirect("/mn");  // Success redirect to /mn
        }
    }
}
