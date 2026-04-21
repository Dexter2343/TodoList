package com.duikt.todolist.controller;

import com.duikt.todolist.dto.user.CreateUserRequest;
import com.duikt.todolist.dto.user.UpdateUserRequest;
import com.duikt.todolist.entity.User;
import com.duikt.todolist.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<?> regUser(@Valid @RequestBody CreateUserRequest request){
        userService.addUser(request.getName(), request.getEmail());
        return ResponseEntity.ok().body("User created successfully");
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser( @PathVariable Long id,
                                         @Valid @RequestBody UpdateUserRequest request) {
        userService.updateUser(id, request.getName(), request.getEmail());
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
