package com.example.task1.controller;

import com.example.task1.model.AuthRequest;
import com.example.task1.config.JWTService;
import com.example.task1.entity.User;
import com.example.task1.repository.UserRepository;
import com.example.task1.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class UserController {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AuthenticationService authenticationService;

    public UserController(
            @Autowired UserRepository userRepository,
            @Autowired JWTService jwtService,
            @Autowired AuthenticationManager authenticationManager,
            @Autowired AuthenticationService authenticationService){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @PostMapping("/authenticate")
    public String authenticateAndGenerateToken(@RequestBody AuthRequest authRequest){
        var authRes =  authenticationService.signIn(authRequest);
        return "SignIn successful";
    }
}
