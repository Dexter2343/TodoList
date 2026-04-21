package com.duikt.todolist.controller;

import com.duikt.todolist.dto.todo.CreateTodoRequest;
import com.duikt.todolist.dto.todo.UpdateTodoRequest;
import com.duikt.todolist.entity.Todo;
import com.duikt.todolist.service.impl.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {

    private final TodoServiceImpl todoService;

    @PostMapping("/users/{userId}/todos")
    public ResponseEntity<String> createTodo(@PathVariable Long userId,
                                             @RequestBody CreateTodoRequest request) {
        todoService.addTodo(userId, request.getTitle(), request.getDescription());
        return ResponseEntity.ok("Todo created successfully");
    }

    @GetMapping("/users/{userId}/todos")
    public List<Todo> getTodosByUserId(@PathVariable Long userId) {
        return todoService.getTodosByUserId(userId);
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.getTodoById(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId,
                                           @RequestBody UpdateTodoRequest request) {
        return ResponseEntity.ok(
                todoService.updateTodo(
                        todoId,
                        request.getTitle(),
                        request.getDescription())
        );
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodoById(todoId);
        return ResponseEntity.ok("Todo deleted successfully");
    }
}
