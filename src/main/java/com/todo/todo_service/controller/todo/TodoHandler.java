package com.todo.todo_service.controller.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_service.controller.todo.dto.TodoRequest;
import com.todo.todo_service.model.Todo;
import com.todo.todo_service.service.TodoService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todos")
public class TodoHandler {

    private final TodoService todoService;

    public TodoHandler(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public Mono<ResponseEntity<Todo>> createTodo(@Valid @RequestBody TodoRequest todo) {
        return todoService.createTodo(todo)
                .map(savedTodo -> ResponseEntity.status(HttpStatus.CREATED).body(savedTodo));
    }
}
