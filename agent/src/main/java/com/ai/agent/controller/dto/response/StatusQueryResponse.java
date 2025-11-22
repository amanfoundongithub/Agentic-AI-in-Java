package com.ai.agent.controller.dto.response;

import com.ai.agent.core.api.AnswerGenerationResponse;
import com.ai.agent.core.enums.JobStatus;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;


/**
 * Response to a query on status of the job
 *
 * @param taskId The ID of the task
 * @param jobStatus The status of the job
 * @param response The response, if any
 */
public record StatusQueryResponse(

        @NotNull(message = "requestId is required")
        @UUID(message = "Valid UUID")
        String taskId,

        @NotNull(message = "Job status is required")
        JobStatus jobStatus,

        Object response
) {}
