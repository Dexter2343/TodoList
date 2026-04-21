package com.duikt.todolist.service.impl;

import com.duikt.todolist.entity.Todo;
import com.duikt.todolist.entity.User;
import com.duikt.todolist.exception.ResourceNotFoundException;
import com.duikt.todolist.repository.TodoRepository;
import com.duikt.todolist.repository.UserRepository;
import com.duikt.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepo;
    private final UserRepository userRepo;

    @Override
    public void addTodo(Long userId, String title, String description) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Todo todo = Todo.builder()
                .title(title)
                .description(description)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        todoRepo.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, String title, String description) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        todo.setTitle(title);
        todo.setDescription(description);
        todo.setUpdatedAt(LocalDateTime.now());

        return todoRepo.save(todo);
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
    }

    @Override
    public List<Todo> getTodosByUserId(Long userId) {
        return todoRepo.findAllByUserId(userId);
    }

    @Override
    public void deleteTodoById(Long id) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        todoRepo.delete(todo);
    }
}
