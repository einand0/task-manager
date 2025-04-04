package com.einando.taskmanager.services;

import com.einando.taskmanager.dto.TaskCreateDTO;
import com.einando.taskmanager.entities.Task;
import com.einando.taskmanager.entities.User;
import com.einando.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUser(User user){
        return taskRepository.findByUser(user);
    }
}
