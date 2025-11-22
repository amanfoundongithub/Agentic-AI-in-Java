package com.ai.agent.controller.rest;


import com.ai.agent.controller.dto.request.AnswerRequestDTO;
import com.ai.agent.controller.dto.response.AgentRequestSubmitted;
import com.ai.agent.controller.dto.response.JobStatusResponse;
import com.ai.agent.controller.service.AgentControllerService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AgentController {

    private final AgentControllerService service;

    @Autowired
    public AgentController(AgentControllerService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public AgentRequestSubmitted generate(@Valid @RequestBody AnswerRequestDTO dto) {
        return service.submitRequest(dto);
    }

    @GetMapping("/status/{taskId}")
    public JobStatusResponse getResult(@PathVariable String taskId) {
        return service.getResponse(taskId);
    }

}
