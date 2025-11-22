package com.ai.agent.controller.dto.response;

import com.ai.agent.core.enums.HealthStatus;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

/**
 * Report regarding the health of the server
 *
 * @param status The health of the server
 * @param timeStamp timeStamp at which the health was reported
 */
public record HealthReport(

        @NotNull(message = "Report is incomplete without status")
        HealthStatus status,

        @NotNull(message = "Timestamp must be there")
        Instant timeStamp
) {}
