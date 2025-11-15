package com.ai.agent.tools.impl;

import com.ai.agent.tools.Tool;
import com.ai.agent.tools.dto.GoogleSearchResults;
import com.ai.agent.tools.dto.GoogleToolResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class GoogleSearchTool implements Tool<List<GoogleToolResults>, String> {

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.cx}")
    private String cx;

    @Value("${google.api.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<GoogleToolResults> execute(String query) {

        String requestUrl = baseUrl
                + "?key=" + apiKey
                + "&cx=" + cx
                + "&q=" + query;

        GoogleSearchResults results = restTemplate.getForObject(requestUrl, GoogleSearchResults.class);

        List<GoogleToolResults> finalResults = new ArrayList<>();

        for(GoogleSearchResults.Item item : results.items) {
            GoogleToolResults result = new GoogleToolResults(item.title, item.snippet, item.link);
            finalResults.add(result);
        }

        return finalResults;

    }
}
