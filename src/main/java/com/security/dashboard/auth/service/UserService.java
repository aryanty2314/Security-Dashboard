package com.security.dashboard.auth.service;

import com.security.dashboard.auth.dto.request.LoginRequest;
import com.security.dashboard.auth.dto.request.RegisterRequest;
import com.security.dashboard.auth.dto.response.AuthResponse;

public interface UserService
{
AuthResponse Register(RegisterRequest registerRequest);


    AuthResponse Login(LoginRequest loginRequest);
}
