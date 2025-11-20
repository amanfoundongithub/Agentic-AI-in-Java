package com.ai.agent.mcp.tool.query.impl;

import com.ai.agent.mcp.tool.query.ToolQuery;

public class WebScrapingQuery extends ToolQuery {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

