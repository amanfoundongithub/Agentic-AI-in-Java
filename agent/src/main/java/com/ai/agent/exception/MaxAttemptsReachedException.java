package com.ai.agent.exception;

public class MaxAttemptsReachedException extends RuntimeException {
    
    public MaxAttemptsReachedException(int maxAttempts) {
        super("The Agent is unable to generate answer even after " + maxAttempts + " attempts.");
    }
    
}
