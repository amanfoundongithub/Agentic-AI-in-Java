package com.ai.agent.llm.model.request;

public class LLMRequest {

    protected String prompt;

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
