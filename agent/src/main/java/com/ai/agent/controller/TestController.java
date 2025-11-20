package com.ai.agent.controller;

import com.ai.agent.core.api.AnswerGenerationRequest;
import com.ai.agent.core.api.AnswerGenerationResponse;
import com.ai.agent.react.ReactAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/agent")
public class TestController {

    private final ReactAgent reactAgent;

    @Autowired
    public TestController(ReactAgent reactAgent) {
        this.reactAgent = reactAgent;
    }

    // Route to test the ReAct agent
    @PostMapping("/run")
    public AnswerGenerationResponse runAgent(@RequestBody AgentRunRequest request) {

        AnswerGenerationRequest llmReq = new AnswerGenerationRequest();
        llmReq.setRequestId(request.getRequestId());
        llmReq.setPrompt(request.getPrompt());
        llmReq.setSystemPrompt(request.getSystemPrompt());

        // Done
        return reactAgent.generate(llmReq, request.getModel());
    }

    // DTO for request body
    public static class AgentRunRequest {
        private String requestId;
        private String prompt;
        private String systemPrompt;
        private String model;

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getRequestId() {
            return requestId;
        }

        public String getPrompt() { return prompt; }
        public void setPrompt(String prompt) { this.prompt = prompt; }

        public String getSystemPrompt() { return systemPrompt; }
        public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
    }
}
