package com.ai.agent.llm.service.impl;

import com.ai.agent.llm.model.request.impl.OllamaRequest;
import com.ai.agent.llm.model.response.impl.OllamaResponse;
import com.ai.agent.llm.service.LLMAbstractService;
import org.springframework.stereotype.Service;


@Service
public class OllamaService extends LLMAbstractService<OllamaRequest, OllamaResponse> {

    public OllamaService() {
        this.url = "http://127.0.0.1:8000/api/llm/generate";
        this.responseClass = OllamaResponse.class;
    }

}
