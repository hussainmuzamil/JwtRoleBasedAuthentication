package com.example.task1.service;

import com.example.task1.model.AuthRequest;
import com.example.task1.model.JwtAuthenticationResponse;
import com.example.task1.config.JWTService;
import com.example.task1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository repository;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;
    @Override
    public JwtAuthenticationResponse signIn(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        var user = repository.findUserByEmail(authRequest.getEmail()).orElseThrow(()->new UsernameNotFoundException("username doesn't exist"));
        var jwt = jwtService.generateToken(user.getEmail());
        var refreshToken = jwtService.regenerateToken(new HashMap<>(),user);
        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse();
        authenticationResponse.setToken(jwt);
        authenticationResponse.setRefreshToken(refreshToken);
        return authenticationResponse;
    }
}
