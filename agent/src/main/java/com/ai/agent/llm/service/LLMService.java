package com.ai.agent.llm.service;

import com.ai.agent.llm.model.LLMRequest;
import com.ai.agent.llm.model.LLMResponse;

public interface LLMService {

    String model();
    LLMResponse generate(LLMRequest request);

}
