package com.ai.agent.controller.dto;

public class AnswerRequestDTO {

    private String requestId;
    private String prompt;
    private String sysPrompt;

    public AnswerRequestDTO() {
    }

    public AnswerRequestDTO(String requestId, String prompt, String sysPrompt) {
        this.requestId = requestId;
        this.prompt = prompt;
        this.sysPrompt = sysPrompt;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSysPrompt() {
        return sysPrompt;
    }

    public void setSysPrompt(String sysPrompt) {
        this.sysPrompt = sysPrompt;
    }

    @Override
    public String toString() {
        return "AnswerRequestDTO{" +
                "requestId='" + requestId + '\'' +
                ", prompt='" + prompt + '\'' +
                ", sysPrompt='" + sysPrompt + '\'' +
                '}';
    }
}
