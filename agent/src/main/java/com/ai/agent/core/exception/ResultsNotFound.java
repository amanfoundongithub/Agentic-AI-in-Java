package com.ai.agent.core.exception;

public class ResultsNotFound extends RuntimeException {

    public ResultsNotFound(String by) {
        super("No Results could be found by " + by);
    }
}
