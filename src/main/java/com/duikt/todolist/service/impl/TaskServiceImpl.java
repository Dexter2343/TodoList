package com.duikt.todolist.service.impl;

import com.duikt.todolist.entity.Task;
import com.duikt.todolist.entity.Todo;
import com.duikt.todolist.exception.ResourceNotFoundException;
import com.duikt.todolist.repository.TaskRepository;
import com.duikt.todolist.repository.TodoRepository;
import com.duikt.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepo;
    private final TodoRepository todoRepo;

    @Override
    public Task addTask(Long todoId, String taskName, String description, LocalDateTime deadline, String status) {

        Todo todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));

        Task task = Task.builder()
                .title(taskName)
                .description(description)
                .deadline(deadline)
                .status(status)
                .todo(todo)
                .build();

        taskRepo.save(task);
        return task;
    }

    @Override
    public Task updateTask(Long id, String taskName, String description, String status) {

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        task.setTitle(taskName);
        task.setDescription(description);
        task.setStatus(status);

        return taskRepo.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Override
    public List<Task> getTasksByTodoId(Long todoId) {
        return taskRepo.findAllByTodoId(todoId); // 🔥 FIX TYPE + naming
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public void deleteTaskById(Long id) {

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepo.delete(task);
    }
}
