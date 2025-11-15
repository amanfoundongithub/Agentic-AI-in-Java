package com.ai.agent.tools.dto.query.impl;

import com.ai.agent.tools.dto.query.ToolQuery;

public class GoogleQuery extends ToolQuery {

    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
