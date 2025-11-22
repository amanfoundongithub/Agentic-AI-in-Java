package com.ai.agent.controller.dto.response;

import com.ai.agent.core.enums.HealthStatus;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record HealthReport(

        @NotNull(message = "Report is incomplete without status")
        HealthStatus status,

        @NotNull(message = "Timestamp must be there")
        Instant timeStamp
) {}
