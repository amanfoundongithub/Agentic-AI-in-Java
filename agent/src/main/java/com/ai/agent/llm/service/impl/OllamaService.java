package com.ai.agent.llm.service.impl;

import com.ai.agent.config.LLMConfig;
import com.ai.agent.llm.dto.LLMResponse;
import com.ai.agent.llm.dto.http.OllamaAPIResponse;
import com.ai.agent.llm.service.LLMAbstractService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OllamaService extends LLMAbstractService<OllamaAPIResponse> {

    @Value("${ollama.api.url}")
    private String requestUrl;

    @PostConstruct
    public void init() {
        this.url = requestUrl;
        this.responseClass = OllamaAPIResponse.class;
    }

    @Override
    public String model() {
        return LLMConfig.OLLAMA_MODEL_NAME;
    }

    @Override
    protected LLMResponse convert(OllamaAPIResponse response) {
        LLMResponse res = new LLMResponse();
        res.setGenerated(true);
        res.setText(response.content.message.content);
        return res;
    }


}
