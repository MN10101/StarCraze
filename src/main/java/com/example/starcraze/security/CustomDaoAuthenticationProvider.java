package com.example.starcraze.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        String rawPassword = authentication.getCredentials().toString();
        String hashedPassword = userDetails.getPassword();

        // Use BCryptPasswordEncoder to validate raw password against hashed password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(rawPassword, hashedPassword)) {
            throw new BadCredentialsException("Bad credentials: Password mismatch");
        }

        // Proceed with standard checks if the password matches
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}



