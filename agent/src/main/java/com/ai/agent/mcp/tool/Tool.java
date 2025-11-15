package com.ai.agent.mcp.tool;

import com.ai.agent.mcp.tool_query.ToolQuery;
import com.ai.agent.mcp.tool_result.ToolResult;

public interface Tool<R extends ToolResult, Q extends ToolQuery>{

    String name();
    String description();
    /**
     * A common interface to execute the action of a tool
     *
     * @param query The entity that contains the requested action items for the tool
     * @return Response from the tool
     */
    R execute(Q query);

}
