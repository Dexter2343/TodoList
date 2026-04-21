package com.duikt.todolist.service;

import com.duikt.todolist.entity.User;

import java.util.List;

public interface UserService {

    void addUser(String name, String email);
    User updateUser(Long id, String name, String email);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUserById(Long id);
}
