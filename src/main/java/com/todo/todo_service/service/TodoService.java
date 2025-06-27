package com.todo.todo_service.service;

import org.springframework.stereotype.Service;

import com.todo.todo_service.controller.todo.dto.TodoRequest;
import com.todo.todo_service.model.Todo;
import com.todo.todo_service.publisher.SnsPublisherService;
import com.todo.todo_service.repository.TodoRepository;
import com.todo.todo_service.service.mapper.TodoMapper;

import reactor.core.publisher.Mono;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final SnsPublisherService snsPublisherService;

    public TodoService(TodoMapper todoMapper, TodoRepository todoRepository, SnsPublisherService snsPublisherService) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
        this.snsPublisherService = snsPublisherService;
    }

    public Mono<Todo> createTodo(TodoRequest todoRequest) {
        return Mono.just(todoRequest)
                .map(this::mapToTodo)
                .flatMap(this::publishToSNS)
                .flatMap(this::saveToDatabase);
    }

    private Todo mapToTodo(TodoRequest todoRequest) {
        return todoMapper.mapToTodo(todoRequest);
    }

    private Mono<Todo> publishToSNS(Todo todo) {

        return snsPublisherService.publishTodoEvent(todo)
                .thenReturn(todo);
    }

    private Mono<Todo> saveToDatabase(Todo todo) {

        return todoRepository.save(todo);
    }
}
