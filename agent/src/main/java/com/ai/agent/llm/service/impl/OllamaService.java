package com.ai.agent.llm.service.impl;

import com.ai.agent.core.api.AnswerGenerationRequest;
import com.ai.agent.core.api.AnswerGenerationResponse;
import com.ai.agent.llm.http.impl.OllamaAPIRequest;
import com.ai.agent.llm.http.impl.OllamaAPIResponse;
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
    protected AnswerGenerationResponse convertResponse(OllamaAPIResponse response) {
        AnswerGenerationResponse res = new AnswerGenerationResponse();
        res.setText(response.content.message.content);
        return res;
    }

    @Override
    protected OllamaAPIRequest convertRequest(AnswerGenerationRequest request) {
        return new OllamaAPIRequest(request.getPrompt(), request.getSystemPrompt());
    }
}
