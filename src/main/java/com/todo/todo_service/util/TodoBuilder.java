package com.todo.todo_service.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.todo.todo_service.model.Todo;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public class TodoBuilder {

    private TodoBuilder() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static Todo fromItem(Map<String, AttributeValue> item) {
        return Todo.builder()
                .id(item.get("id").s())
                .userId(item.get("userId").s())
                .title(item.get("todoTitle").s())
                .description(item.get("todoDescription").s())
                .dueDate(LocalDate.parse(item.get("dueDate").s()))
                .createdAt(LocalDate.parse(item.get("createdAt").s()))
                .updatedAt(LocalDate.parse(item.get("updatedAt").s()))
                .completed(item.get("completed").bool())
                .active(Integer.parseInt(item.get("active").n()))
                .build();
    }

    public static Map<String, AttributeValue> toItem(Todo todo) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(todo.getId()).build());
        item.put("todoTitle", AttributeValue.builder().s(todo.getTitle()).build());
        item.put("todoDescription", AttributeValue.builder().s(todo.getDescription()).build());
        item.put("dueDate", AttributeValue.builder().s(todo.getDueDate().toString()).build());
        item.put("createdAt", AttributeValue.builder().s(todo.getCreatedAt().toString()).build());
        item.put("updatedAt", AttributeValue.builder().s(todo.getUpdatedAt().toString()).build());
        item.put("completed", AttributeValue.builder().bool(todo.isCompleted()).build());
        item.put("active", AttributeValue.builder().n(String.valueOf(todo.getActive())).build());

        return item;
    }

}
