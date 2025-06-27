package com.todo.todo_service.handler.todo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoRequest {
    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Title is required")
    private String title;

    @Size(max = 150, message = "Description must not exceed 150 characters")
    private String description;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;
}
