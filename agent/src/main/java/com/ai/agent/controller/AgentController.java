package com.ai.agent.controller;


import com.ai.agent.controller.dto.AnswerRequestDTO;
import com.ai.agent.controller.mapper.AnswerGenerationRequestMapper;
import com.ai.agent.core.db.ResultDB;
import com.ai.agent.core.job.AgentJobAllocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AgentController {

    private final AgentJobAllocator agentJobAllocator;
    private final ResultDB resultDB;

    @Autowired
    public AgentController(
            AgentJobAllocator agentJobAllocator,
            ResultDB resultDB
    ) {
        this.agentJobAllocator = agentJobAllocator;
        this.resultDB = resultDB;
    }

    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generate(@RequestBody AnswerRequestDTO dto) {
        String taskId = agentJobAllocator.allocate(new AnswerGenerationRequestMapper(dto), dto.getModel());

        return ResponseEntity.accepted().body(
                Map.of(
                        "taskId", taskId,
                        "status", "queued"
                )
        );
    }

    @GetMapping("/result/{taskId}")
    public ResponseEntity<?> getResult(@PathVariable String taskId) {

        Object result = resultDB.get(taskId);

        return ResponseEntity.ok(Objects.requireNonNullElseGet(result, () -> Map.of(
                "taskId", taskId,
                "status", "processing"
        )));

    }

}
