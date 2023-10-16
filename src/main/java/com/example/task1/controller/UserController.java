package com.example.task1.controller;

import com.example.task1.entity.User;
import com.example.task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;


    public UserController(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }
}
