package com.ai.agent.controller.mapper;

import com.ai.agent.controller.dto.request.AgentRequest;
import com.ai.agent.core.api.AnswerGenerationRequest;


/**
 * Mapper to convert a request DTO to a request for ReactAgent, using
 * Adapter pattern.
 *
 * @author amanfoundongithub
 */
public class AnswerGenerationRequestMapper extends AnswerGenerationRequest {

    private final AgentRequest requestDTO;

    public AnswerGenerationRequestMapper(AgentRequest requestDTO) {
        this.requestDTO = requestDTO;
    }

    @Override
    public String getRequestId() {
        return this.requestDTO.requestId();
    }

    @Override
    public String getPrompt() {
        return this.requestDTO.prompt();
    }

    @Override
    public String getSystemPrompt() {
        return this.requestDTO.sysPrompt();
    }
}
