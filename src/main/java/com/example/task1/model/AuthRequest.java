package com.example.task1.model;

import lombok.Data;

@Data
public class AuthRequest {
    private final String email;
    private final String password;
}
