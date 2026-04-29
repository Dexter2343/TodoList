package com.duikt.todolist.controller;

import com.duikt.todolist.dto.task.CreateTaskRequest;
import com.duikt.todolist.dto.task.UpdateTaskRequest;
import com.duikt.todolist.entity.Task;
import com.duikt.todolist.entity.Todo;
import com.duikt.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/todos/{todoId}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable Long todoId,
                                           @RequestBody CreateTaskRequest request) {

        Task created = taskService.addTask(
                todoId,
                request.getTitle(),
                request.getDescription(),
                request.getStatus()
        );

        return ResponseEntity.ok(created);
    }

    @GetMapping("/todos/{todoId}/tasks")
    public ResponseEntity<List<Todo>> getTasksByTodoId(@PathVariable Long todoId) {

        List<Todo> tasks = taskService.getTasksByTodoId(todoId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId,
                                           @RequestBody UpdateTaskRequest request) {

        return ResponseEntity.ok(
                taskService.updateTask(
                        taskId,
                        request.getTaskName(),
                        request.getDescription(),
                        request.getStatus()
                )
        );
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.noContent().build();
    }
}