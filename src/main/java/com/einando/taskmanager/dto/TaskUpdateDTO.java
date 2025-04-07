package com.einando.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TaskUpdateDTO", description = "Objeto para atualizar os dados de uma tarefa")
public class TaskUpdateDTO {

    @Schema(description = "Novo título da tarefa", example = "Atualizar projeto com Swagger")
    private String title;

    @Schema(description = "Nova descrição da tarefa", example = "Adicionar documentação aos endpoints")
    private String description;

    @Schema(description = "Novo status da tarefa", example = "true")
    private boolean completed;

    public TaskUpdateDTO() {
    }

    public TaskUpdateDTO(String title, String description, boolean completed) {
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
}
