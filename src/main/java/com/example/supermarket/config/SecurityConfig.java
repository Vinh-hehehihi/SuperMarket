package com.example.supermarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Cho phép TẤT CẢ các request
                )
                .csrf(csrf -> csrf.disable()) // Tắt CSRF để test Postman/Form dễ hơn
                .formLogin(login -> login.disable()) // Tắt form login mặc định
                .httpBasic(basic -> basic.disable()); // Tắt popup đăng nhập

        return http.build();
    }
}