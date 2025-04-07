package com.einando.taskmanager.dto;

import com.einando.taskmanager.entities.Task;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TaskResponseDTO", description = "Dados retornados ao buscar uma tarefa")
public class TaskResponseDTO {

    @Schema(description = "ID da tarefa", example = "1")
    private Long id;

    @Schema(description = "Título da tarefa", example = "Estudar Spring Boot")
    private String title;

    @Schema(description = "Descrição da tarefa", example = "Fazer o CRUD completo com autenticação")
    private String description;

    @Schema(description = "Status da tarefa", example = "true")
    private boolean completed;

    @Schema(description = "Nome de usuário do criador da tarefa", example = "einand0")
    private String username;

    public TaskResponseDTO() {}

    public TaskResponseDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.completed = task.isCompleted();
        this.username = task.getUser().getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
