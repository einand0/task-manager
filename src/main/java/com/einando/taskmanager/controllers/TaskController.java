package com.einando.taskmanager.controllers;

import com.einando.taskmanager.dto.TaskCreateDTO;
import com.einando.taskmanager.dto.TaskResponseDTO;
import com.einando.taskmanager.dto.TaskUpdateDTO;
import com.einando.taskmanager.entities.Task;
import com.einando.taskmanager.entities.User;
import com.einando.taskmanager.security.UserDetailsImpl;
import com.einando.taskmanager.services.TaskService;

import com.einando.taskmanager.utils.UserRole;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.access.AccessDeniedException;
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

    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        User user = userDetailsImpl.getUser();

        if(user.getRoles().contains(UserRole.ADMIN)){
            List<Task> tasks = taskService.getAllTasks();
            return ResponseEntity.status((HttpStatus.FOUND)).body(tasks);
        } else{
            throw new AccessDeniedException("Apenas administradores podem acessar todas as tarefas.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Task foundedTask = taskService.findTaskById(id);
        if (!foundedTask.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para executar esta ação.");
        }

        taskService.deleteTaskById(id);
        return ResponseEntity.ok("Tarefa deletada.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody TaskUpdateDTO dto){
        Task foundedTask = taskService.findTaskById(id);
        if (!foundedTask.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para executar esta ação.");
        }

        taskService.updateTask(id, dto);
        return ResponseEntity.ok().body(new TaskResponseDTO(foundedTask));
    }
}
