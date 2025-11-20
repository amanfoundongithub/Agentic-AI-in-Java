package com.ai.agent.llm.http.impl;

import com.ai.agent.llm.http.LLMHttpRequest;

public class OllamaAPIRequest extends LLMHttpRequest {

    public final String prompt;
    public final String systemPrompt;

    public OllamaAPIRequest(String prompt, String systemPrompt) {
        this.prompt = prompt;
        this.systemPrompt = systemPrompt;
    }
}
