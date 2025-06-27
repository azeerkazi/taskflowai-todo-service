package com.todo.todo_service.service.mapper;

import java.util.UUID;
import java.time.LocalDate;
import com.todo.todo_service.model.Todo;
import org.springframework.stereotype.Component;
import com.todo.todo_service.handler.todo.dto.TodoRequest;

@Component
public class TodoMapper {

    public Todo mapToTodo(TodoRequest todoRequest) {

        LocalDate currentDate = LocalDate.now();

        return Todo.builder()
                .id(UUID.randomUUID().toString())
                .userId(todoRequest.getUserId())
                .title(todoRequest.getTitle())
                .description(todoRequest.getDescription())
                .dueDate(todoRequest.getDueDate())
                .createdAt(currentDate)
                .updatedAt(currentDate)
                .completed(false)
                .active(1)
                .build();
    }
}
