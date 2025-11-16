package com.ai.agent.exception;

public class UserPromptNotPresentException extends RuntimeException {
    public UserPromptNotPresentException() {
        super("User Prompt is not provided; Plesse provide one");
    }
}
