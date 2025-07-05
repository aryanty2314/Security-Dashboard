package com.security.dashboard.auth.controller;

import com.security.dashboard.auth.entity.Token;
import com.security.dashboard.auth.entity.User;
import com.security.dashboard.auth.jwt.JwtService;
import com.security.dashboard.auth.repository.TokenRepository;
import com.security.dashboard.auth.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/refresh")
@RequiredArgsConstructor
public class RefreshTokenController
{
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String email = jwtService.extractEmail(refreshToken);

        // 1. Validate refresh token signature
        if (!jwtService.isTokenValid(refreshToken, email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Token");
        }

        // 2. Check in DB if token exists and is not revoked/expired
        Optional<Token> storedToken = tokenRepository.findByRefreshTokenAndRevokedFalseAndExpiredFalse(refreshToken);
        if (storedToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token Reused or Revoked");
        }

        // 3. Get user and generate new tokens
        User user = userRepository.findByEmail(email).orElseThrow();
        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        // 4. Revoke old token
        Token token = storedToken.get();
        token.setRevoked(true);
        token.setExpired(true);
        tokenRepository.save(token);



        // 5. Save new refresh token
        saveRefreshToken(newRefreshToken, user);

        // 6. Return new tokens
        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken
        ));
    }
    private void saveRefreshToken(String refreshToken, User user) {
        Token token = Token.builder()
                .refreshToken(refreshToken)
                .userId(user.getId())
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }


}
