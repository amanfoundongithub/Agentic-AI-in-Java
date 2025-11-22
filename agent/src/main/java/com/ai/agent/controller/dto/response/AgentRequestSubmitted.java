package com.ai.agent.controller.dto.response;

import com.ai.agent.core.enums.JobStatus;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

import java.time.Instant;


/**
 * Response message for the job being sent to the pool for execution
 *
 * @param requestId The unique UUID used for tracking the request
 * @param timeStamp The timestamp at which the request was sent to the orchestrator
 * @param jobStatus The status of the job, being QUEUED or ERROR
 */
public record AgentRequestSubmitted(

        @NotNull(message = "ID is required for Redis DB access")
        @UUID(message = "Valid UUID is needed")
        String requestId,

        @NotNull(message = "Required timestamp of creation")
        Instant timeStamp,

        @NotNull(message = "Status of the job")
        JobStatus jobStatus
) {}
