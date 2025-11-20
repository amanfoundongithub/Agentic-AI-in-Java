package com.ai.agent.mcp.tool.result.impl;

import com.ai.agent.mcp.tool.result.ToolResult;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchResult extends ToolResult {

    private List<SearchInfo> results = new ArrayList<>();

    public List<SearchInfo> getResults() {
        return results;
    }

    public void add(SearchInfo info) {
        results.add(info);
    }

    public void add(String title, String snippet, String link) {
        results.add(new SearchInfo(title, snippet, link));
    }

    public static class SearchInfo {
        private String title;
        private String snippet;
        private String link;

        public SearchInfo(String title, String snippet, String link) {
            this.title = title;
            this.snippet = snippet;
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public String getSnippet() {
            return snippet;
        }

        public String getLink() {
            return link;
        }
    }
}
