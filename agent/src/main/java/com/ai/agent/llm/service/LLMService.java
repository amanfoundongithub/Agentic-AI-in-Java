package com.ai.agent.llm.service;

import com.ai.agent.llm.model.request.LLMRequest;
import com.ai.agent.llm.model.response.LLMResponse;

public interface LLMService<REQ extends LLMRequest> {

    String model();

    LLMResponse generate(REQ request);

}
