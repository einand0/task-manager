package com.einando.taskmanager.dto;

import com.einando.taskmanager.entities.Task;
import com.einando.taskmanager.entities.User;

public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private String username;

    public TaskResponseDTO(){

    }

    public TaskResponseDTO(Task task){
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
