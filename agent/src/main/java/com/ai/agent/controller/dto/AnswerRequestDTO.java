package com.ai.agent.controller.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;


/**
 * Record class for the Answer Request in RestController for Agent
 *
 * @param requestId The unique UUID corresponding to the request
 * @param prompt The prompt/instruction for the agent to generate a response
 * @param sysPrompt The system instructions to the agent, if any
 * @param model The model to be used for generation
 */
public record AnswerRequestDTO(

        @NotBlank(message = "requestId is required for tracking request")
        @UUID(message = "requestId must be a valid UUID")
        String requestId,

        @NotBlank(message = "prompt is required for generating response")
        @Size(min = 1, max = 2000, message = "prompt must be between 1 to 2000 characters")
        String prompt,

        @NotBlank(message = "model is required to select the model to generate response")
        String model,

        @Size(max = 2000, message = "sysPrompt cannot exceed more than 2000 characters")
        String sysPrompt) {}