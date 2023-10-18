package com.example.task1.controller;

import com.example.task1.entity.User;
import com.example.task1.model.AuthRequest;
import com.example.task1.model.JwtAuthenticationResponse;
import com.example.task1.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody AuthRequest authRequest){
        var authRes =  authenticationService.signIn(authRequest);
//        return "Sign in successfully";
        return authRes;

    }
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody User user){
        return authenticationService.signUp(user);
    }
}
