package com.ai.agent.llm.dto.http.impl;

import com.ai.agent.llm.dto.http.LLMHttpRequest;

public class OllamaAPIRequest extends LLMHttpRequest {

    public final String prompt;
    public final String systemPrompt;

    public OllamaAPIRequest(String prompt, String systemPrompt) {
        this.prompt = prompt;
        this.systemPrompt = systemPrompt;
    }
}
