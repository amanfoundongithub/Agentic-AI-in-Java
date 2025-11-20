package com.ai.agent.llm.http.impl;

import com.ai.agent.llm.http.LLMHttpResponse;

public class OllamaAPIResponse extends LLMHttpResponse {

    public boolean isGenerated;

    public OllamaAPIResponse.Content content;

    public static class Content {

        public String model;
        public String created_at;

        public boolean done;

        public String done_reason;

        public OllamaAPIResponse.Content.Message message;

        public static class Message {
            public String role;
            public String content;
        }

    }
}
