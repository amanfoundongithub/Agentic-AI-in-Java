package com.ai.agent.llm.model.request.impl;

import com.ai.agent.llm.model.request.LLMRequest;

public class OllamaRequest extends LLMRequest {

    private String systemPrompt;

    public void setSystemPrompt(String systemPrompt) {
        this.systemPrompt = systemPrompt;
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }

}
