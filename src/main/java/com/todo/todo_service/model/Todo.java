package com.todo.todo_service.model;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDate;

@Data
@Builder
public class Todo {

    private String id;
    private String userId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean completed;
    private int active;

}