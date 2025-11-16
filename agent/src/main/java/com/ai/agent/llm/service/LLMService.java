package com.ai.agent.llm.service;

import com.ai.agent.llm.dto.LLMRequest;
import com.ai.agent.llm.dto.LLMResponse;

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
    LLMResponse generate(LLMRequest request);

}
