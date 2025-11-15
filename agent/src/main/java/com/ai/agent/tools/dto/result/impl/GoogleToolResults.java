package com.ai.agent.tools.dto.result.impl;

import com.ai.agent.tools.dto.result.ToolResult;

import java.util.ArrayList;
import java.util.List;

public class GoogleToolResults extends ToolResult {

    private List<SearchInfo> results = new ArrayList<>();

    public List<SearchInfo> getResults() {
        return results;
    }

    public void add(SearchInfo info) {
        results.add(info);
    }

    public void add(String title, String snippet) {
        results.add(new SearchInfo(title, snippet));
    }

    public static class SearchInfo {
        private String title;
        private String snippet;

        public SearchInfo(String title, String snippet) {
            this.title = title;
            this.snippet = snippet;
        }

        public String getTitle() {
            return title;
        }

        public String getSnippet() {
            return snippet;
        }
    }
}
