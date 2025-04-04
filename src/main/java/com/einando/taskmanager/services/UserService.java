package com.einando.taskmanager.services;

import com.einando.taskmanager.dto.UserCreateDTO;
import com.einando.taskmanager.dto.UserResponseDTO;
import com.einando.taskmanager.entities.User;
import com.einando.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User createUser(UserCreateDTO dto){
        return userRepository.save(new User(dto));
    }
}
