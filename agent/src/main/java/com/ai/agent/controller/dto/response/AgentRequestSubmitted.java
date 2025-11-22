package com.ai.agent.controller.dto.response;

import com.ai.agent.core.enums.JobStatus;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

import java.time.Instant;

public record AgentRequestSubmitted(

        @NotNull(message = "ID is required for Redis DB access")
        @UUID(message = "Valid UUID is needed")
        String requestId,

        @NotNull(message = "Required timestamp of creation")
        Instant timeStamp,

        @NotNull(message = "Status of the job")
        JobStatus jobStatus
) {}
