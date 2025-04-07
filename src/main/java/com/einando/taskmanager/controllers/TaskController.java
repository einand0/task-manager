package com.einando.taskmanager.controllers;

import com.einando.taskmanager.dto.TaskCreateDTO;
import com.einando.taskmanager.dto.TaskResponseDTO;
import com.einando.taskmanager.dto.TaskUpdateDTO;
import com.einando.taskmanager.entities.Task;
import com.einando.taskmanager.entities.User;
import com.einando.taskmanager.security.UserDetailsImpl;
import com.einando.taskmanager.services.TaskService;

import com.einando.taskmanager.utils.UserRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.access.AccessDeniedException;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Gerenciamento de tarefas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Cria uma nova tarefa", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
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
    @Operation(summary = "Busca todas as tarefas do usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    @GetMapping
    public ResponseEntity<List<Task>> getUserTasks(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        List<Task> tasks = taskService.getTasksByUser(userDetailsImpl.getUser());
        return ResponseEntity.status(HttpStatus.FOUND).body(tasks);
    }

    @Operation(summary = "Lista todas as tarefas", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
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

    @Operation(summary = "Deleta uma tarefa", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Task foundedTask = taskService.findTaskById(id);
        if (!foundedTask.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para executar esta ação.");
        }

        taskService.deleteTaskById(id);
        return ResponseEntity.ok("Tarefa deletada.");
    }

    @Operation(summary = "Atualiza uma tarefa existente", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
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
