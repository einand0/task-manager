package com.einando.taskmanager.controllers;

import com.einando.taskmanager.dto.UserCreateDTO;
import com.einando.taskmanager.dto.UserLoginDTO;
import com.einando.taskmanager.dto.UserResponseDTO;
import com.einando.taskmanager.entities.User;
import com.einando.taskmanager.security.JwtUtil;
import com.einando.taskmanager.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = userService.createUser(dto);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        String token = jwtUtil.generateToken(dto.getEmail());

        return ResponseEntity.ok(token);


    }
}
