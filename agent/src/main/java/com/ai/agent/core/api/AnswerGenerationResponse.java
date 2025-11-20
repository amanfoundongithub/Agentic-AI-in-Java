package com.ai.agent.core.api;

import java.time.LocalDateTime;

public class AnswerGenerationResponse {

    // Audit details
    private String requestId;
    private LocalDateTime requestDate;

    // If generated or not
    private boolean generated;
    private String errorMessage;

    // Generated text
    private String text;

    public AnswerGenerationResponse() {
        this.requestDate = LocalDateTime.now();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
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
