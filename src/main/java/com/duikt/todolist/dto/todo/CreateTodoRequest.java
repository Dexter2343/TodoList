package com.duikt.todolist.dto.todo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoRequest {
    @NotBlank(message = "Title cannot be empty")
    @Max(value = 100, message = "Title cannot be longer than 100 characters")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    @Max(value = 100, message = "Description cannot be longer than 100 characters")
    private String description;

    @NotBlank(message = "Status cannot be empty")
    @Max(value = 20, message = "Status cannot be longer than 20 characters")
    private String status;

    @NotBlank(message = "CreatedAt cannot be empty")
    @Max(value = 20, message = "CreatedAt cannot be longer than 20 characters")
    private LocalDateTime createdAt;
}
