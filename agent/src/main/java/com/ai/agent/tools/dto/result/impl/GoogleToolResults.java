package com.ai.agent.tools.dto.result.impl;

import com.ai.agent.tools.dto.result.ToolResult;

import java.util.List;

/**
 * A class to store all the Google Search Results
 *
 */
public class GoogleToolResults extends ToolResult {

    private List<SearchInfo> results;

    public static class SearchInfo {
        private String title;
        private String snippet;

        public SearchInfo(String title, String snippet) {
            this.title = title;
            this.snippet = snippet;
        }

        @Override
        public String toString() {
            return "{'title':'" + title +
                         "','info':" + snippet + "'}";
        }
    }

    public void add(SearchInfo info) {
        results.add(info);
    }

    public void add(String title, String snippet) {
        SearchInfo info = new SearchInfo(title, snippet);
        results.add(info);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        for(SearchInfo result : results) {
            String resultInfo = result.toString();
            info.append(resultInfo);
        }
        return info.toString();
    }
}
