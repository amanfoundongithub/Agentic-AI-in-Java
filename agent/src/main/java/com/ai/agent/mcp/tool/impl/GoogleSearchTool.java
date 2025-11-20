package com.ai.agent.mcp.tool.impl;

import com.ai.agent.core.exception.ResultsNotFound;
import com.ai.agent.mcp.tool.ToolAbstract;
import com.ai.agent.mcp.tool.query.impl.GoogleSearchQuery;
import com.ai.agent.mcp.tool.result.impl.GoogleSearchResult;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.List;
import java.util.Map;

@Component
public class GoogleSearchTool extends ToolAbstract<GoogleSearchResult, GoogleSearchQuery> {

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
    protected GoogleSearchResult processingLogic(GoogleSearchQuery query) {
        //
        GoogleSearchResult finalResults = new GoogleSearchResult();

        // Make a request URL
        String requestUrl = baseUrl
                + "?key=" + apiKey
                + "&cx=" + cx
                + "&q=" + query.getQuery();

        // Send GET request
        Map<String, Object> results = webClient
                .get()
                .uri(requestUrl)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        ClientResponse::createException
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        if (results == null) {
            throw new ResultsNotFound("Google Search");
        }

        // Extract items
        List<Map<String, Object>> items = (List<Map<String, Object>>) results.get("items");

        if (items != null) {
            for (Map<String, Object> item : items) {
                String title = (String) item.get("title");
                String snippet = (String) item.get("snippet");
                String link = (String) item.get("link");
                finalResults.add(title, snippet, link);

            }
            return finalResults;

        } else {
            throw new ResultsNotFound("Google Search");
        }
    }

    @Override
    protected GoogleSearchResult processingError() {
        return new GoogleSearchResult();
    }
}
