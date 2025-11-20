package com.ai.agent.core.exception;

public class LLMNotFoundException extends RuntimeException {

    public LLMNotFoundException(String name) {
        super("No LLM Service found for the name:" + name + ". Please check the name!");
    }

}
