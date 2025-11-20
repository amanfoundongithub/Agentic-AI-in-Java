package com.ai.agent.common.exception;

public class UserPromptNotPresentException extends RuntimeException {
    public UserPromptNotPresentException() {
        super("User Prompt is not provided; Plesse provide one");
    }
}
