package com.ai.agent.llm.model.response.impl;

import com.ai.agent.llm.model.response.LLMResponse;

public class OllamaResponse extends LLMResponse {

    public boolean isGenerated;

    public Content content;

    public static class Content {

        public String model;
        public String created_at;

        public boolean done;

        public String done_reason;

        public Message message;

        public static class Message {
            public String role;
            public String content;
        }

    }

}
