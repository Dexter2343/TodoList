package com.duikt.todolist.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    private String taskName;
    private String description;
    private String status;
}
