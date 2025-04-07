package com.einando.taskmanager.controllers;

import com.einando.taskmanager.dto.UserCreateDTO;
import com.einando.taskmanager.dto.UserLoginDTO;
import com.einando.taskmanager.dto.UserResponseDTO;
import com.einando.taskmanager.entities.User;
import com.einando.taskmanager.security.JwtUtil;
import com.einando.taskmanager.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticação", description = "Login e registro de usuários")
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

    @Operation(summary = "Registra um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = userService.createUser(dto);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @Operation(summary = "Realiza login e retorna um token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        String token = jwtUtil.generateToken(dto.getEmail());

        return ResponseEntity.ok(token);


    }
}
