package com.ai.agent.controller.mapper;

import com.ai.agent.controller.dto.AnswerRequestDTO;
import com.ai.agent.core.api.AnswerGenerationRequest;

public class AnswerGenerationRequestMapper extends AnswerGenerationRequest {

    private final AnswerRequestDTO requestDTO;

    public AnswerGenerationRequestMapper(AnswerRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    @Override
    public String getRequestId() {
        return this.requestDTO.getRequestId();
    }

    @Override
    public String getPrompt() {
        return this.requestDTO.getPrompt();
    }

    @Override
    public String getSystemPrompt() {
        return this.requestDTO.getSysPrompt();
    }
}
