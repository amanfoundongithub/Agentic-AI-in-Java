package com.ai.agent.common.exception;

public class LLMNotFoundException extends RuntimeException {

    public LLMNotFoundException(String name) {
        super("No LLM Service found for the name:" + name + ". Please check the name!");
    }

}
