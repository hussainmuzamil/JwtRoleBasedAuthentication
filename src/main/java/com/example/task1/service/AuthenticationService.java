package com.example.task1.service;

import com.example.task1.model.AuthRequest;
import com.example.task1.model.JwtAuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public JwtAuthenticationResponse signIn(AuthRequest authRequest);
}
