package com.duikt.todolist.dto.user;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;
}
