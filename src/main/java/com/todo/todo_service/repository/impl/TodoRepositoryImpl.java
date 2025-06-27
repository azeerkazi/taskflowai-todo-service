package com.todo.todo_service.repository.impl;

import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.todo.todo_service.model.Todo;
import com.todo.todo_service.repository.TodoRepository;
import com.todo.todo_service.util.TodoBuilder;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final DynamoDbAsyncClient dynamoDbAsyncClient;

    @Value("${cloud.aws.dynamodb.table-name}")
    private String todoTable;

    public TodoRepositoryImpl(DynamoDbAsyncClient dynamoDbAsyncClient) {
        this.dynamoDbAsyncClient = dynamoDbAsyncClient;
    }

    @Override
    public Mono<Todo> save(Todo todo) {

        Map<String, AttributeValue> item = TodoBuilder.toItem(todo);
        PutItemRequest request = PutItemRequest.builder()
                .tableName(todoTable)
                .item(item)
                .build();
        return Mono.fromFuture(dynamoDbAsyncClient.putItem(request)).thenReturn(todo);
    }

}
