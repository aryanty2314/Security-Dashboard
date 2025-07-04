package com.security.dashboard.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse
{

    private String token;
    private String refreshToken;
    private String userName;
    private String email;
    private boolean twoFactorEnabled;

}
