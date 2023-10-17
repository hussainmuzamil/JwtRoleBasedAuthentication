package com.example.task1.service;

import com.example.task1.entity.Role;
import com.example.task1.entity.User;
import com.example.task1.model.AuthRequest;
import com.example.task1.model.JwtAuthenticationResponse;
import com.example.task1.config.JWTService;
import com.example.task1.repository.RoleRepository;
import com.example.task1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final PasswordEncoder passwordEncoder;
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
    @Override
    public String signUp(User user) {
            User u1 = new User();
            u1.setName(user.getName());
            Role userRl = roleRepository.findByName("USER");
            u1.setRoles(Arrays.asList(userRl));
            u1.setEmail(user.getEmail());
            u1.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(u1);
            return "User save successfully";
    }
}
