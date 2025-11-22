package com.ai.agent.controller.rest;


import com.ai.agent.controller.dto.request.AnswerRequestDTO;
import com.ai.agent.controller.dto.response.AgentRequestSubmitted;
import com.ai.agent.controller.dto.response.JobStatusResponse;
import com.ai.agent.controller.service.AgentControllerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Agent Controller", description = "API interface to ReACT agent")
@CrossOrigin
@RequestMapping("/api/v1")
public class AgentController {

    private final AgentControllerService service;

    @Autowired
    public AgentController(AgentControllerService service) {
        this.service = service;
    }

    @Operation(summary = "Sends the request to Orchestrator for generation")
    @PostMapping("/generate")
    public AgentRequestSubmitted generate(@Valid @RequestBody AnswerRequestDTO dto) {
        return service.submitRequest(dto);
    }

    @Operation(summary = "Status of the generation task")
    @GetMapping("/status/{taskId}")
    public JobStatusResponse getResult(@PathVariable String taskId) {
        return service.getResponse(taskId);
    }

}
