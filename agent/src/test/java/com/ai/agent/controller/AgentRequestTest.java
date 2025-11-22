package com.ai.agent.controller;


import com.ai.agent.controller.dto.request.AgentRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AgentRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void testAgentRequest_WithValidRequest() {
        AgentRequest req = new AgentRequest(
                "123e4567-e89b-12d3-a456-426614174000",
                "Hello, generate something.",
                "gpt-5",
                "You are helpful."
        );

        Set<ConstraintViolation<AgentRequest>> violations = validator.validate(req);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testAgentRequest_WithInvalidUUID() {
        AgentRequest req = new AgentRequest(
                "not-a-uuid",
                "hello",
                "gpt-5",
                null
        );

        Set<ConstraintViolation<AgentRequest>> violations = validator.validate(req);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("requestId must be a valid UUID")));
    }

    @Test
    void testAgentRequest_WithBlankUUID() {
        AgentRequest req = new AgentRequest(
                "",
                "hello",
                "gpt-5",
                null
        );

        Set<ConstraintViolation<AgentRequest>> violations = validator.validate(req);

        assertFalse(violations.isEmpty());
        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getMessage().equals("requestId is required for tracking request"))
        );
    }

    @Test
    void testAgentRequest_WithBlankPrompt() {
        AgentRequest req = new AgentRequest(
                "123e4567-e89b-12d3-a456-426614174000",
                "",
                "gpt-5",
                null
        );

        Set<ConstraintViolation<AgentRequest>> violations = validator.validate(req);

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getMessage().equals("prompt is required for generating response"))
        );
    }

    @Test
    void testAgentRequest_WithLongPrompt() {
        String longPrompt = "A".repeat(2001);

        AgentRequest req = new AgentRequest(
                "123e4567-e89b-12d3-a456-426614174000",
                longPrompt,
                "gpt-5",
                null
        );

        Set<ConstraintViolation<AgentRequest>> violations = validator.validate(req);

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getMessage().equals("prompt must be between 1 to 2000 characters"))
        );
    }

    @Test
    void testAgentRequest_WithBlanKModel() {
        AgentRequest req = new AgentRequest(
                "123e4567-e89b-12d3-a456-426614174000",
                "hello",
                "",
                null
        );

        Set<ConstraintViolation<AgentRequest>> violations = validator.validate(req);

        assertFalse(violations.isEmpty());
        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getMessage().equals("model is required to select the model to generate response"))
        );
    }

    @Test
    void testAgentRequest_WithLongSystemPrompt() {
        String longSysPrompt = "B".repeat(2001);

        AgentRequest req = new AgentRequest(
                "123e4567-e89b-12d3-a456-426614174000",
                "valid prompt",
                "gpt-5",
                longSysPrompt
        );

        Set<ConstraintViolation<AgentRequest>> violations = validator.validate(req);

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getMessage().equals("sysPrompt cannot exceed more than 2000 characters"))
        );
    }




}
