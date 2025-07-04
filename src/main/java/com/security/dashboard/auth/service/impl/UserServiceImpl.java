package com.security.dashboard.auth.service.impl;

import com.security.dashboard.auth.dto.request.LoginRequest;
import com.security.dashboard.auth.dto.request.RegisterRequest;
import com.security.dashboard.auth.dto.response.AuthResponse;
import com.security.dashboard.auth.entity.User;
import com.security.dashboard.auth.jwt.JwtService;
import com.security.dashboard.auth.mapper.UserMapper;
import com.security.dashboard.auth.repository.UserRepository;
import com.security.dashboard.auth.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public AuthResponse Register(RegisterRequest registerRequest)
    {

        if (userRepository.existsByEmail(registerRequest.getEmail()))
        {
            throw new RuntimeException("User already exists with email: " + registerRequest.getEmail());
        }
        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);


        String accessToken = jwtService.generateToken(user.getEmail(), Map.of("role", user.getRole().name()));
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken, user.getUsername(), user.getEmail(), user.isTwoFactorEnabled());
    }

    @Override
    public AuthResponse Login(LoginRequest loginRequest)
    {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getPassword(), loginRequest.getEmail())
    );

    User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequest.getEmail()));

    String accessToken = jwtService.generateToken(user.getEmail(), Map.of("role", user.getRole().name()));
    String refreshToken = jwtService.generateRefreshToken(user.getEmail());

    return new AuthResponse(accessToken, refreshToken, user.getUsername(), user.getEmail(), user.isTwoFactorEnabled());
    }
}
