package com.einando.taskmanager.controllers;

import com.einando.taskmanager.dto.TaskCreateDTO;
import com.einando.taskmanager.dto.TaskResponseDTO;
import com.einando.taskmanager.entities.Task;
import com.einando.taskmanager.security.UserDetailsImpl;
import com.einando.taskmanager.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskCreateDTO dto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        Task newTask = new Task();
        newTask.setTitle(dto.getTitle());
        newTask.setDescription(dto.getDescription());
        newTask.setCompleted(dto.isCompleted());
        newTask.setUser(userDetailsImpl.getUser());
        System.out.println(newTask);
        taskService.createTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskResponseDTO(newTask));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getUserTasks(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        List<Task> tasks = taskService.getTasksByUser(userDetailsImpl.getUser());
        return ResponseEntity.status(HttpStatus.FOUND).body(tasks);
    }
}
