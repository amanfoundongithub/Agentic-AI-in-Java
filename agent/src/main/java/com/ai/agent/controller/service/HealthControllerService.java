package com.ai.agent.controller.service;


import com.ai.agent.controller.dto.response.HealthReport;
import com.ai.agent.core.enums.HealthStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class HealthControllerService {

    public HealthReport check() {
        return new HealthReport(HealthStatus.UP, Instant.now());
    }

}
