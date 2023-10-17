package com.example.task1.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
}
