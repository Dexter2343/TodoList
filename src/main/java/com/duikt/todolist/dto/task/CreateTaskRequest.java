package com.duikt.todolist.dto.task;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
    @NotBlank(message = "Title cannot be empty")
    @Max(value = 100, message = "Title cannot be longer than 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @Max(value = 100, message = "Description cannot be longer than 100 characters")
    private String description;

    @NotBlank(message = "Status cannot be empty")
    @Max(value = 20, message = "Status cannot be longer than 20 characters")
    private String status;

    @NotBlank(message = "Deadline cannot be empty")
    private LocalDateTime deadline;
}
