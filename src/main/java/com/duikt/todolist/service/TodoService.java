package com.duikt.todolist.service;

import com.duikt.todolist.dto.todo.CreateTodoRequest;
import com.duikt.todolist.entity.Todo;
import com.duikt.todolist.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {
    void addTodo(Long userId, String title, String description);

    Todo updateTodo(Long id, String title, String description);

    Todo getTodoById(Long id);
    List<Todo> getTodosByUserId(Long userId);

    void deleteTodoById(Long id);
}
