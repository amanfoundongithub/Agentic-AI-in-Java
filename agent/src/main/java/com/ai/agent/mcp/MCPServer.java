package com.ai.agent.mcp;

import com.ai.agent.mcp.tool.Tool;
import com.ai.agent.mcp.tool.query.ToolQuery;
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


    public Tool<?, ?> getTool(String name) {
        return toolsMap.get(name);
    }

    public String showAllAvailableTools() {
        StringBuilder toolsSection = new StringBuilder("Available tools:\n");

        toolsMap.forEach((name, tool) -> {
            toolsSection.append("- ").append(name).append(":\n");
            toolsSection.append("  description: ").append(tool.description()).append("\n");
            toolsSection.append("  input_format: {\n");

            Class<? extends ToolQuery> qClass = tool.queryClass();
            if (qClass != null) {
                for (var field : qClass.getDeclaredFields()) {
                    toolsSection.append("    \"")
                            .append(field.getName())
                            .append("\": \"")
                            .append(field.getType().getSimpleName())
                            .append("\"\n");
                }
            }

            toolsSection.append("  }\n\n");
        });

        return toolsSection.toString();
    }

}
