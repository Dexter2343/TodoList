package com.duikt.todolist.service;

import com.duikt.todolist.entity.Task;
import com.duikt.todolist.entity.Todo;

import java.util.List;

public interface TaskService {
    Task addTask(Long todoId, String taskName, String description, String status);
    Task updateTask(Long id,String taskName,String description, String status);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    List<Todo> getTasksByTodoId(Long todoId);
    void deleteTaskById(Long id);

}
