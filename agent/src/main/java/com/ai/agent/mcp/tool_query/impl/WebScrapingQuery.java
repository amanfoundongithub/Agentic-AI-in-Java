package com.ai.agent.mcp.tool_query.impl;

import com.ai.agent.mcp.tool_query.ToolQuery;

public class WebScrapingQuery extends ToolQuery {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

