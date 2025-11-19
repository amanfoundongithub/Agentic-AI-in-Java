package com.ai.agent.llm.service;

import com.ai.agent.llm.dto.LLMRequest;
import com.ai.agent.llm.dto.LLMResponse;
import com.ai.agent.llm.dto.http.LLMHttpRequest;
import com.ai.agent.llm.dto.http.LLMHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


/**
 * Abstract implementation of the HTTP method for the LLMService
 *
 * @author amanfoundongithub
 *
 * @param <R> The HTTP response model expected for the API response
 * @param <Q> The HTTP request  model expected for the API request
 */
public abstract class LLMAbstractService<Q extends LLMHttpRequest, R extends LLMHttpResponse> implements LLMService {

    // Rest Template for REST exchange
    protected static final RestTemplate restTemplate = new RestTemplate();

    // Logger to log details from the LLM
    protected final Logger llmLogger = LoggerFactory.getLogger(getClass());

    // Variables to be injected
    protected String url;
    protected String model;
    protected Class<R> responseClass;


    public String model() {
        return model;
    }

    public LLMResponse generate(LLMRequest request) {

        // Log the request received
        llmLogger.info("\tINFO: Request with requestId {} for LLM Answer Generation Received", request.getRequestId());

        // Tell that we are starting to generate answer
        llmLogger.info("\tINFO: Starting the generation...");

        // Create a response object to be sent back, populate with default values
        LLMResponse response = new LLMResponse();

        response.setRequestId(request.getRequestId());
        response.setGenerated(false);

        // Main service loop begins here
        try {

            // Get the headers and add to the body
            HttpHeaders headers = getHeaders();
            HttpEntity<Q> httpEntity = new HttpEntity<>(convertRequest(request), headers);

            // POST request sent
            ResponseEntity<R> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    responseClass
            );

            // Replace with converted body for the ease of use
            response = convertResponse(responseEntity.getBody());

        } catch (Exception e) {
            llmLogger.error("\tERROR: {}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        } finally {
            llmLogger.info("\tINFO: Request with requestId {} for LLM Answer Generation Completed\n", request.getRequestId());
        }

        return response;
    }


    // Helper function to get headers
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    // Auxiliary function to convert HTTP Response to desired response
    protected abstract LLMResponse convertResponse(R response);

    // Auxiliary function to convert request to desired HTTP Request
    protected abstract Q convertRequest(LLMRequest request);
}
