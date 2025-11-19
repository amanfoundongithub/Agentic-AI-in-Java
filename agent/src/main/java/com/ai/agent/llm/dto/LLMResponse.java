package com.ai.agent.llm.dto;

import java.time.LocalDateTime;

public class LLMResponse {

    // Audit details
    private String requestId;
    private LocalDateTime requestDate;

    // If generated or not
    private boolean generated;
    private String errorMessage;

    // Generated text
    private String text;

    public LLMResponse() {
        this.requestDate = LocalDateTime.now();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

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
