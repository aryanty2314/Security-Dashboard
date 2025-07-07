package com.security.dashboard.auth.controller;

import com.security.dashboard.auth.dto.request.LoginRequest;
import com.security.dashboard.auth.dto.request.RegisterRequest;
import com.security.dashboard.auth.dto.response.AuthResponse;
import com.security.dashboard.auth.entity.Token;
import com.security.dashboard.auth.repository.TokenRepository;
import com.security.dashboard.auth.repository.UserRepository;
import com.security.dashboard.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController
{
    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenRepository tokenRepository;

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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Optional<Token> storedToken = tokenRepository.findByRefreshTokenAndRevokedFalseAndExpiredFalse(token);
        storedToken.ifPresent(t -> {
            t.setRevoked(true);
            t.setExpired(true);
            tokenRepository.save(t);
        });

        return ResponseEntity.ok("Logged out successfully");
    }

}
