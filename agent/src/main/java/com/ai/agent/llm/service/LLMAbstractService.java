package com.ai.agent.llm.service;

import com.ai.agent.llm.dto.LLMRequest;
import com.ai.agent.llm.dto.LLMResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


/**
 * Abstract implementation of the HTTP method for the LLMService
 *
 * @author amanfoundongithub
 *
 * @param <H> The HTTP response model expected for the API response
 */
public abstract class LLMAbstractService<H> implements LLMService {

    // Rest Template
    protected static final RestTemplate restTemplate = new RestTemplate();

    // Logger
    protected final Logger llmLogger = LoggerFactory.getLogger(getClass());

    protected String url;
    protected Class<H> responseClass;


    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    protected abstract LLMResponse convert(H response);

    public LLMResponse generate(LLMRequest request) {

        // Log the request received
        llmLogger.info("Request {} for LLM Generation Received", request);

        // Create a response object to be sent
        LLMResponse response = new LLMResponse();
        response.setGenerated(false);

        try {
            // Get the headers and add to the body
            HttpHeaders headers = getHeaders();
            HttpEntity<LLMRequest> httpEntity = new HttpEntity<>(request, headers);

            // POST request sent
            ResponseEntity<H> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    responseClass
            );

            // Send the body converted
            response = convert(responseEntity.getBody());

        } catch (Exception e) {
            llmLogger.error("Error In Processing Request: {}", e.getMessage());
        } finally {
            llmLogger.info("Request {} for LLM Generation Completed", request);
        }

        return response;
    }
}
