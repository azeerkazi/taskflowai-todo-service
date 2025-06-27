package com.todo.todo_service.event;

import com.todo.todo_service.model.Todo;

public record TodoEventMessage(
        String eventType,
        String timeStamp,
        Todo data) {
}