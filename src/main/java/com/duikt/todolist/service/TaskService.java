package com.duikt.todolist.service;

import com.duikt.todolist.entity.Task;
import com.duikt.todolist.entity.Todo;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    Task addTask(Long todoId, String taskName, String description, LocalDateTime deadline, String status);
    Task updateTask(Long id,String taskName,String description, String status);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    List<Task> getTasksByTodoId(Long todoId);
    void deleteTaskById(Long id);

}
