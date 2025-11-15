package com.ai.agent.tools.impl;

import com.ai.agent.tools.Tool;
import com.ai.agent.tools.dto.query.impl.GoogleQuery;
import com.ai.agent.tools.dto.result.impl.GoogleToolResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
public class GoogleSearchTool implements Tool<GoogleToolResults, GoogleQuery> {

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.cx}")
    private String cx;

    @Value("${google.api.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public GoogleToolResults execute(GoogleQuery query) {

        String requestUrl = baseUrl
                + "?key=" + apiKey
                + "&cx=" + cx
                + "&q=" + query.getQuery();

        Map results = restTemplate.getForObject(requestUrl, Map.class);

        GoogleToolResults finalResults = new GoogleToolResults();
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
