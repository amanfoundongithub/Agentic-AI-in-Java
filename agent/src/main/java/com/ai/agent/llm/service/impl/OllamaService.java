package com.ai.agent.llm.service.impl;

import com.ai.agent.llm.model.request.impl.OllamaRequest;
import com.ai.agent.llm.model.response.LLMResponse;
import com.ai.agent.llm.model.response.impl.OllamaAPIResponse;
import com.ai.agent.llm.service.LLMAbstractService;
import org.springframework.stereotype.Component;

@Component
public class OllamaService extends LLMAbstractService<OllamaRequest, OllamaAPIResponse> {

    public OllamaService() {
        this.url = "http://127.0.0.1:8000/api/llm/generate";
        this.responseClass = OllamaAPIResponse.class;
    }

    @Override
    public String model() {
        return "llama3.2";
    }

    @Override
    protected LLMResponse convert(OllamaAPIResponse response) {
        LLMResponse res = new LLMResponse();
        res.text = response.content.message.content;
        return res;
    }


}
