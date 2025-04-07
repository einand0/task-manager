package com.einando.taskmanager.dto;

import com.einando.taskmanager.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TaskCreateDTO", description = "Objeto para criação de tarefas")
public class TaskCreateDTO {
    @Schema(description = "Título da tarefa", example = "Estudar Spring Boot")
    private String title;
    @Schema(description = "Descrição detalhada da tarefa", example = "Assistir aula e implementar exemplo de JWT")
    private String description;
    @Schema(description = "Status da tarefa", example = "false")
    private boolean completed;
    @Schema(hidden = true)
    private User user;

    public TaskCreateDTO(){

    }
    public TaskCreateDTO(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
