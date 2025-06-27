package com.todo.todo_service.repository;

import com.todo.todo_service.model.Todo;

import reactor.core.publisher.Mono;

public interface TodoRepository {
    
    Mono<Todo> save(Todo todo);
    
}
