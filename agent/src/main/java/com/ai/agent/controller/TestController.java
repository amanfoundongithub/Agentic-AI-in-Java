package com.ai.agent.controller;

import com.ai.agent.llm.dto.LLMRequest;
import com.ai.agent.llm.dto.LLMResponse;
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
    public LLMResponse runAgent(@RequestBody AgentRunRequest request) {

        LLMRequest llmReq = new LLMRequest();
        llmReq.setPrompt(request.getPrompt());
        llmReq.setSystemPrompt(request.getSystemPrompt());

        return reactAgent.generate(llmReq, request.getModel());
    }

    // DTO for request body
    public static class AgentRunRequest {
        private String prompt;
        private String systemPrompt;
        private String model;

        public String getPrompt() { return prompt; }
        public void setPrompt(String prompt) { this.prompt = prompt; }

        public String getSystemPrompt() { return systemPrompt; }
        public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
    }
}
