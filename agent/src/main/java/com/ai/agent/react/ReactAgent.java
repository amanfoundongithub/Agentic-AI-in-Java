package com.ai.agent.react;

import com.ai.agent.llm.LLMContext;
import com.ai.agent.mcp.MCPServer;
import org.springframework.stereotype.Component;

@Component
public class ReactAgent {

    private final MCPServer mcpServer;
    private final LLMContext llmContext;

    public ReactAgent(MCPServer mcpServer, LLMContext llmContext) {
        this.mcpServer = mcpServer;
        this.llmContext = llmContext;
    }


}
