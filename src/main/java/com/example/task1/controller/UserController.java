package com.example.task1.controller;

<<<<<<< HEAD
import com.example.task1.entity.Delivery;
import com.example.task1.model.AuthRequest;
import com.example.task1.config.JWTService;
import com.example.task1.entity.User;
import com.example.task1.repository.DeliveryRepository;
=======
import com.example.task1.model.AuthRequest;
import com.example.task1.config.JWTService;
import com.example.task1.entity.User;
>>>>>>> 8d9d15d4dfa3119c9bc50c2519de168900b042b9
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
<<<<<<< HEAD
    private final DeliveryRepository deliveryRepository;
=======
>>>>>>> 8d9d15d4dfa3119c9bc50c2519de168900b042b9
    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }

<<<<<<< HEAD
    @GetMapping("/delivery-info")
    public ResponseEntity<List<Delivery>> getDeliveries(){
        return  ResponseEntity.ok(deliveryRepository.findAll());
    }
=======
>>>>>>> 8d9d15d4dfa3119c9bc50c2519de168900b042b9

}
