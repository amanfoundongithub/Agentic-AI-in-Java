package com.ai.agent.mcp.tool.query.impl;

import com.ai.agent.mcp.tool.query.ToolQuery;

public class GoogleSearchQuery extends ToolQuery {

    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
