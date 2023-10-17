package com.example.task1.service;

import com.example.task1.entity.User;
import com.example.task1.model.AuthRequest;
import com.example.task1.model.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public ResponseEntity<JwtAuthenticationResponse> signIn(AuthRequest authRequest);
    public ResponseEntity<String> signUp(User user);
}
