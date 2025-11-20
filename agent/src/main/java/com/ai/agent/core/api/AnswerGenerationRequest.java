package com.ai.agent.core.api;

import java.util.UUID;

public class AnswerGenerationRequest {

    // Audit details for tracking the request
    private String requestId;

    // Meta data present in the request
    private String prompt;
    private String systemPrompt;

    public AnswerGenerationRequest() {

    }

    public AnswerGenerationRequest(String prompt, String systemPrompt) {
        this.requestId = UUID.randomUUID().toString();
        this.prompt = prompt;
        this.systemPrompt = systemPrompt;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setSystemPrompt(String systemPrompt) {
        this.systemPrompt = systemPrompt;
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }

}
