package com.ai.agent.llm.dto;

public class LLMResponse {

    private boolean generated;
    private String text;

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isGenerated() {
        return generated;
    }

    public String getText() {
        return text;
    }
}
