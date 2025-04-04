package com.einando.taskmanager.controllers;

import com.einando.taskmanager.dto.UserCreateDTO;
import com.einando.taskmanager.dto.UserResponseDTO;
import com.einando.taskmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO dto){
        UserResponseDTO newUser = userService.createUser(dto);
        return ResponseEntity.ok().body(newUser);
    }
}
