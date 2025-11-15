package com.ai.agent.mcp.tool_query.impl;

import com.ai.agent.mcp.tool_query.ToolQuery;

public class GoogleSearchQuery extends ToolQuery {

    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
