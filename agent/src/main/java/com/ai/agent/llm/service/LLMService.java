package com.ai.agent.llm.service;

import com.ai.agent.core.api.AnswerGenerationRequest;
import com.ai.agent.core.api.AnswerGenerationResponse;

/**
 * Interface for the LLM Service provided via APIs
 *
 * @author amanfoundongithub
 */
public interface LLMService {

    String model();

    /**
     * Generate the response based on the request
     *
     * @param request The request for the LLM containing system and user prompts
     * @return A response body containing the response from LLM
     */
    AnswerGenerationResponse generate(AnswerGenerationRequest request);

}
