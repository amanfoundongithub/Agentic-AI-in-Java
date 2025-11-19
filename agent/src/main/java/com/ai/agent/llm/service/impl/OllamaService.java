package com.ai.agent.llm.service.impl;

import com.ai.agent.llm.dto.LLMRequest;
import com.ai.agent.llm.dto.LLMResponse;
import com.ai.agent.llm.dto.http.impl.OllamaAPIRequest;
import com.ai.agent.llm.dto.http.impl.OllamaAPIResponse;
import com.ai.agent.llm.service.LLMAbstractService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OllamaService extends LLMAbstractService<OllamaAPIRequest, OllamaAPIResponse> {

    @Value("${ollama_service.url}")
    private String requestUrl;

    @Value("${ollama_service.model}")
    private String requestModel;

    @PostConstruct
    public void init() {
        this.url = requestUrl;
        this.model = requestModel;
        this.responseClass = OllamaAPIResponse.class;
    }

    @Override
    protected LLMResponse convertResponse(OllamaAPIResponse response) {
        LLMResponse res = new LLMResponse();
        res.setGenerated(true);
        res.setText(response.content.message.content);
        return res;
    }

    @Override
    protected OllamaAPIRequest convertRequest(LLMRequest request) {
        return new OllamaAPIRequest(request.getPrompt(), request.getSystemPrompt());
    }
}
