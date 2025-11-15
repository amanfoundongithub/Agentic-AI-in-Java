package com.ai.agent.react;

import com.ai.agent.mcp.MCPServer;
import org.springframework.stereotype.Component;

@Component
public class ReactAgent {

    private final MCPServer mcpServer;

    public ReactAgent(MCPServer mcpServer) {
        this.mcpServer = mcpServer;
    }



}
