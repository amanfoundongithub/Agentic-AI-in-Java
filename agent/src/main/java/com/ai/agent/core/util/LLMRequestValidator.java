package com.ai.agent.core.util;

import com.ai.agent.core.exception.InvalidRequest;
import com.ai.agent.core.api.AnswerGenerationRequest;



public class LLMRequestValidator {

    private LLMRequestValidator() {}

    /**
     * Validates the LLM request, throws an error if it is deemed invalid
     *
     * @param request The request to be validated
     * @throws InvalidRequest if the request is invalid
     */
    public static void validate(AnswerGenerationRequest request) throws InvalidRequest {

        if(request == null) {
            throw new InvalidRequest("ERR: Missing request. No request is provided. Provide one to generate response");
        }

        if(request.getRequestId() == null) {
            throw new InvalidRequest("ERR: Missing requestId. This is needed to track the request's status.");
        }

        if(request.getPrompt() == null) {
            throw new InvalidRequest("ERR: Missing prompt. The agent cannot generate response without a prompt from user.");
        }

    }
}
