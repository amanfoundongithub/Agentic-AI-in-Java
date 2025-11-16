package com.ai.agent.llm.model;

public class LLMRequest {

    private String prompt;
    private String systemPrompt;

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
