package com.ai.agent.mcp.tool;

import com.ai.agent.mcp.tool_query.ToolQuery;
import com.ai.agent.mcp.tool_result.ToolResult;

public abstract class ToolAbstract<R extends ToolResult, Q extends ToolQuery> implements Tool<R, Q>{

    protected String name;
    protected String description;
    protected Class<Q> queryClass;

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Class<Q> queryClass() {
        return queryClass;
    }


}
