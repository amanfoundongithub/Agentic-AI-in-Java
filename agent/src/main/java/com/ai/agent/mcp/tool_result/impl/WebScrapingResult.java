package com.ai.agent.mcp.tool_result.impl;

import com.ai.agent.mcp.tool_result.ToolResult;

public class WebScrapingResult extends ToolResult {

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


}
