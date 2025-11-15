package com.ai.agent.mcp.tool.impl;

import com.ai.agent.mcp.tool.Tool;
import com.ai.agent.mcp.tool_query.impl.GoogleSearchQuery;
import com.ai.agent.mcp.tool_result.impl.GoogleSearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Component
public class GoogleSearchTool implements Tool<GoogleSearchResult, GoogleSearchQuery> {

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.cx}")
    private String cx;

    @Value("${google.api.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String name() {
        return "google_search_tool";
    }

    @Override
    public String description() {
        return "Fetches results from Google search";
    }

    @Override
    public GoogleSearchResult execute(GoogleSearchQuery query) {

        String requestUrl = baseUrl
                + "?key=" + apiKey
                + "&cx=" + cx
                + "&q=" + query.getQuery();

        Map results = restTemplate.getForObject(requestUrl, Map.class);

        GoogleSearchResult finalResults = new GoogleSearchResult();
        List<Map<String, Object>> items = (List<Map<String, Object>>) results.get("items");

        if (items != null) {
            for (Map<String, Object> item : items) {
                String title = (String) item.get("title");
                String snippet = (String) item.get("snippet");

                finalResults.add(title, snippet);

            }
        }
        return finalResults;

    }
}
