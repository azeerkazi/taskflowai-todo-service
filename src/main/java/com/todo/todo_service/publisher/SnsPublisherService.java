package com.todo.todo_service.publisher;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.todo_service.event.TodoEventMessage;
import com.todo.todo_service.model.Todo;

import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class SnsPublisherService {

    private final SnsAsyncClient snsAsyncClient;
    private final ObjectMapper objectMapper;
    private final String topicArn;
    private static final Logger log = LoggerFactory.getLogger(SnsPublisherService.class);

    public SnsPublisherService(SnsAsyncClient snsAsyncClient, @Value("${cloud.aws.sns.topic-arn}") String topicArn,
            ObjectMapper objectMapper) {
        this.snsAsyncClient = snsAsyncClient;
        this.topicArn = topicArn;
        this.objectMapper = objectMapper;
    }

    public Mono<Void> publishTodoEvent(Todo todo) {

        TodoEventMessage event = new TodoEventMessage(
                "TODO_CREATED",
                Instant.now().toString(),
                todo);

        String message;

        try {
            message = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            return Mono.error(new RuntimeException("Failed to serialize the event message", e));
        }

        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .build();

        return Mono.fromFuture(snsAsyncClient.publish(request))
                .doOnSuccess(response -> log.info("SNS publish successful, MessageId: {}", response.messageId()))
                .doOnError(error -> log.error("SNS publish failed", error))
                .then();
    }
}
