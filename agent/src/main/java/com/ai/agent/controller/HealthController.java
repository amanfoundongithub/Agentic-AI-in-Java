package com.ai.agent.controller;


import com.ai.agent.controller.dto.response.HealthReport;
import com.ai.agent.controller.service.HealthControllerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Controller", description = "Check the health of the API")
@CrossOrigin
@RequestMapping("/api/health")
public class HealthController {

    private final HealthControllerService service;
    public HealthController(HealthControllerService service) {
        this.service = service;
    }


    @Operation(summary = "OVERALL Health Check report of the server")
    @GetMapping("/check")
    @ApiResponse(responseCode = "200", description = "Health check completed successfully")
    @ApiResponse(responseCode = "500", description = "Internal Server Error during health check")
    public HealthReport check() {
        return service.check();
    }


}
