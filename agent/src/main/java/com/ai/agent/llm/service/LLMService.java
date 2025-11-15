package com.ai.agent.llm.service;

import com.ai.agent.llm.model.request.LLMRequest;
import com.ai.agent.llm.model.response.LLMResponse;

public interface LLMService<REQ extends LLMRequest, RES extends LLMResponse> {

    String model();

    RES generate(REQ request);

}
