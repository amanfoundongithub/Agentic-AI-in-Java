package com.ai.agent.mcp.tool;

import com.ai.agent.mcp.tool.query.ToolQuery;
import com.ai.agent.mcp.tool.result.ToolResult;

/**
 * A generic interface for tools used in MCP.
 *
 * @param <R> The response type returned by the tool.
 * @param <Q> The query type accepted by the tool.
 *
 * @author amanfoundongithub
 */
public interface Tool<R extends ToolResult, Q extends ToolQuery>{

    String name();
    String description();

    // Class of the query
    Class<Q> queryClass();

    /**
     * A common interface to execute the action of a tool
     *
     * @param query The entity that contains the requested action items for the tool
     * @return Response from the tool
     */
    R execute(Q query, String sessionId);

}
