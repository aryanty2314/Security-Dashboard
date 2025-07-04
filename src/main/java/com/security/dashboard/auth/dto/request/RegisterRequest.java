package com.security.dashboard.auth.dto.request;


import lombok.Data;

// This class represents a request to register a new user in the application.
@Data
public class RegisterRequest
{
    private String name;
    private String email;
    private String password;
}
