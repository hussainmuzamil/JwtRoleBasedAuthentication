package com.example.task1.controller;

import com.example.task1.model.AuthRequest;
import com.example.task1.config.JWTService;
import com.example.task1.entity.User;
import com.example.task1.repository.UserRepository;
import com.example.task1.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }


}
