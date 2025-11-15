package com.ai.agent.mcp;

import com.ai.agent.mcp.tool.Tool;
import com.ai.agent.mcp.tool_query.ToolQuery;
import com.ai.agent.mcp.tool_result.ToolResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class MCPServer {

    // Map of all tool names
    Map<String, Tool<?, ?>> toolsMap = new HashMap<>();

    @Autowired
    public MCPServer(List<Tool<?, ?>> tools) {
        for (Tool<?, ?> tool : tools) {
            this.toolsMap.put(tool.name(), tool);
        }
    }

    @SuppressWarnings("unchecked")
    public<R extends ToolResult, Q extends ToolQuery>  R execute(String name, Q query) {
        Tool<R, Q> tool = (Tool<R, Q>) toolsMap.get(name);

        if(tool == null) {
            throw new IllegalArgumentException("No tool found with the name: " + name);
        } else {
            return tool.execute(query);
        }

    }

}
