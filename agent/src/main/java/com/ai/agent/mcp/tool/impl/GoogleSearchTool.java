package com.ai.agent.mcp.tool.impl;

import com.ai.agent.mcp.tool.Tool;
import com.ai.agent.mcp.tool_query.impl.GoogleSearchQuery;
import com.ai.agent.mcp.tool_result.impl.GoogleSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Component
public class GoogleSearchTool implements Tool<GoogleSearchResult, GoogleSearchQuery> {

    // Rest Template
    private static final RestTemplate restTemplate = new RestTemplate();

    // Logger for logging tool requests
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSearchTool.class);

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.cx}")
    private String cx;

    @Value("${google.api.url}")
    private String baseUrl;

    @Value("${google_search.name}")
    private String toolName;

    @Value("${google_search.description}")
    private String toolDescription;


    @Override
    public String name() {
        return toolName;
    }

    @Override
    public String description() {
        return toolDescription;
    }

    @Override
    public Class<GoogleSearchQuery> queryClass() {
        return GoogleSearchQuery.class;
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
