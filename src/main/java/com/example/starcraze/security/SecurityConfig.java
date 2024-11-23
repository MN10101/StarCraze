package com.example.starcraze.security;

import com.example.starcraze.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;


    private PasswordEncoder passwordEncoder;


    @Autowired
    @Lazy
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT u.username, a.authority " +
                "FROM users u " +
                "JOIN authorities a ON u.id = a.user_id " +
                "WHERE u.username = ?");
        return manager;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(DataSource dataSource) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsManager(dataSource));
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(config -> config
                        // Public endpoints
                        .requestMatchers(
                                "/ca",
                                "/reg",
                                "/log",
                                "/horoscope-register",
                                "/forgot-password",
                                "/reset-password-request",
                                "/reset-password",
                                "/error",
                                "/favicon.ico"
                        ).permitAll()
                        .requestMatchers(
                                "/CSS/**",
                                "/Images/**",
                                "/JS/**",
                                "/Video/**",
                                "/webfonts/**"
                        ).permitAll()
                        .requestMatchers(
                                "/privacy-policy",
                                "/terms-and-conditions",
                                "/latest-blogs"
                        ).permitAll()

                        // Allow unauthenticated access to API endpoints for testing
                        .requestMatchers(
                                "/api/**"
                        ).permitAll()

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/horoscope-log")
                        .loginProcessingUrl("/authenticate")
                        .defaultSuccessUrl("/mn", true)
                        .failureUrl("/horoscope-log?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/horoscope-log?logout")
                        .permitAll()
                );

        return http.build();
    }

}
