package com.einando.taskmanager.services;

import com.einando.taskmanager.dto.TaskUpdateDTO;
import com.einando.taskmanager.entities.Task;
import com.einando.taskmanager.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;



@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask() {
        Task task = new Task();
        task.setTitle("Teste");

        when(taskRepository.save(task)).thenReturn(task);

        //Act
        Task resultado = taskService.createTask(task);

        //Assert
        assertNotNull(resultado);
        assertEquals("Teste", resultado.getTitle());

    }

    @Test
    void updateTaskCaseSuccess() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        task.setTitle("Antigo título");
        task.setDescription("Antiga descrição");
        task.setCompleted(false);

        TaskUpdateDTO dto = new TaskUpdateDTO();
        dto.setTitle("Novo título");
        dto.setDescription("Nova descrição");
        dto.setCompleted(true);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task resultado = taskService.updateTask(id, dto);

        assertEquals("Novo título", resultado.getTitle());
        assertEquals("Nova descrição", resultado.getDescription());
        assertTrue(resultado.isCompleted());

        verify(taskRepository).findById(id);
        verify(taskRepository).save(task);
    }

    @Test
    void updateTaskNotFound() {
        Long id = 1L;

        TaskUpdateDTO dto = new TaskUpdateDTO();
        dto.setTitle("Novo título");
        dto.setDescription("Nova descrição");
        dto.setCompleted(true);

        // Simula que a tarefa com o ID não foi encontrada
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        // Verifica se a exceção correta é lançada
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            taskService.updateTask(id, dto);
        });

        // Verifica a mensagem da exceção
        assertEquals("Tarefa não encontrada!", exception.getMessage());

        // Verifica se o método save NUNCA foi chamado, já que não encontrou a tarefa
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void deleteTaskById() {
        Long id = 1L;

        taskService.deleteTaskById(id);

        verify(taskRepository, times(1)).deleteById(id);
    }


    @Test
    void findTaskById() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        task.setTitle("Test");
        task.setDescription("Founded task");
        task.setCompleted(false);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        Task resultado = taskService.findTaskById(id);

        assertNotNull(resultado);
        assertEquals("Test", resultado.getTitle());
        assertEquals("Founded task", resultado.getDescription());
        assertEquals(false, resultado.isCompleted());

        verify(taskRepository).findById(id);
    }
}