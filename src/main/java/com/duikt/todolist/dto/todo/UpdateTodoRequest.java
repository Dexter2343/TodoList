package com.duikt.todolist.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoRequest {
    private String title;
    private String description;
    private String status;
    private LocalDateTime updatedAt;
}
