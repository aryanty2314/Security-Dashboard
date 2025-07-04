package com.security.dashboard.auth.controller;

import com.security.dashboard.auth.dto.request.LoginRequest;
import com.security.dashboard.auth.dto.request.RegisterRequest;
import com.security.dashboard.auth.dto.response.AuthResponse;
import com.security.dashboard.auth.repository.UserRepository;
import com.security.dashboard.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController
{
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> SignUp(@RequestBody RegisterRequest registerRequest)
    {
        AuthResponse response = userService.Register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> SignIn(@RequestBody LoginRequest loginRequest)
    {
        AuthResponse response = userService.Login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
