package com.ai.agent.mcp.tool.impl;

import com.ai.agent.exception.ResultsNotFound;
import com.ai.agent.mcp.tool.ToolAbstract;
import com.ai.agent.mcp.tool_query.impl.GoogleSearchQuery;
import com.ai.agent.mcp.tool_result.impl.GoogleSearchResult;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Component
public class GoogleSearchTool extends ToolAbstract<GoogleSearchResult, GoogleSearchQuery> {

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

    @PostConstruct
    public void init() {
        this.name = toolName;
        this.description = toolDescription;
        this.queryClass = GoogleSearchQuery.class;
    }

    @Override
    public GoogleSearchResult execute(GoogleSearchQuery query) {

        LOGGER.info("Request {} for Google Search Received", query);
        GoogleSearchResult finalResults = new GoogleSearchResult();

        try {

            // Make a request URL
            String requestUrl = baseUrl
                    + "?key=" + apiKey
                    + "&cx=" + cx
                    + "&q=" + query.getQuery();

            // Send GET request
            Map results = restTemplate.getForObject(requestUrl, Map.class);
            if(results == null) {
                throw new ResultsNotFound("Google Search");
            }

            List<Map<String, Object>> items = (List<Map<String, Object>>) results.get("items");

            if (items != null) {
                for (Map<String, Object> item : items) {
                    String title = (String) item.get("title");
                    String snippet = (String) item.get("snippet");
                    String link = (String) item.get("link");
                    finalResults.add(title, snippet, link);

                }
            }

        } catch (Exception e) {
            LOGGER.error("Error in Google Search : {}", e.getMessage());
        } finally {
            LOGGER.info("Request {} for Google Search Completed", query);
        }

        return finalResults;

    }
}
