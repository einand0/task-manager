package com.einando.taskmanager.services;

import com.einando.taskmanager.dto.TaskCreateDTO;
import com.einando.taskmanager.entities.Task;
import com.einando.taskmanager.entities.User;
import com.einando.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada!"));
    }

    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }
}
