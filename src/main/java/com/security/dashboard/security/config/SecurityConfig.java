package com.security.dashboard.security.config;

import com.security.dashboard.security.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityConfig
{

private final JwtAuthFilter jwtAuthFilter;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
{
return http.
        csrf(csrf->csrf.disable())
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/**").permitAll()
                .anyRequest().authenticated())
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}

@Bean
@Primary
public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception
{
    return auth.getAuthenticationManager();
}

@Bean
    public PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}
}
