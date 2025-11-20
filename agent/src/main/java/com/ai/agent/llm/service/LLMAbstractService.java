package com.ai.agent.llm.service;

import com.ai.agent.core.api.AnswerGenerationRequest;
import com.ai.agent.core.api.AnswerGenerationResponse;
import com.ai.agent.llm.dto.http.LLMHttpRequest;
import com.ai.agent.llm.dto.http.LLMHttpResponse;
import com.ai.agent.persistence.dao.LLMEntityDao;
import com.ai.agent.persistence.entity.LLMEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;


/**
 * Abstract implementation of the HTTP method for the LLMService
 *
 * @author amanfoundongithub
 *
 * @param <R> The HTTP response model expected for the API response
 * @param <Q> The HTTP request  model expected for the API request
 */
public abstract class LLMAbstractService<Q extends LLMHttpRequest, R extends LLMHttpResponse> implements LLMService {

    @Autowired
    LLMEntityDao llmEntityDao;

    @Autowired
    WebClient webClient;

    // Logger to log details from the LLM
    protected final Logger llmLogger = LoggerFactory.getLogger(getClass());

    // Variables to be injected
    protected String url;
    protected String model;
    protected Class<R> responseClass;

    public String model() {
        return model;
    }

    public AnswerGenerationResponse generate(AnswerGenerationRequest request) {

        // Log the request received
        llmLogger.info("\tINFO: Request with requestId {} for LLM Answer Generation Received", request.getRequestId());

        // Tell that we are starting to generate answer
        llmLogger.info("\tINFO: Starting the generation...");

        // Create a response object to be sent back, populate with default values
        AnswerGenerationResponse response = new AnswerGenerationResponse();

        response.setRequestId(request.getRequestId());
        response.setGenerated(false);

        // Main service loop begins here
        try {

            // Send API using WebClient
            R responseEntity = webClient
                    .post()
                    .uri(url)
                    .headers(h -> h.addAll(getHeaders()))
                    .bodyValue(convertRequest(request))
                    .retrieve()
                    .bodyToMono(responseClass)
                    .block();

            // Convert main fields for safety
            AnswerGenerationResponse dupResponse = convertResponse(responseEntity);

            // Set up the text and generated status
            response.setGenerated(true);
            response.setText(dupResponse.getText());

        } catch (WebClientResponseException e) {
            llmLogger.info("\tERROR: {} (Response)", e.getMessage());
            response.setErrorMessage("API ERROR: " + e.getStatusCode() + "->" + e.getResponseBodyAsString());
        } catch (WebClientRequestException e) {
            llmLogger.info("\tERROR: {} (Request)", e.getMessage());
            response.setErrorMessage("NETWORK ERROR: " + e.getMessage());
        }
        catch (Exception e) {
            llmLogger.error("\tERROR: {} (System)", e.getMessage());
            response.setErrorMessage(e.getMessage());
        } finally {
            llmLogger.info("\tINFO: Request with requestId {} for LLM Answer Generation Completed\n", request.getRequestId());
        }

        // Log to MongoDB before sending back
        logToDB(response);

        return response;
    }

    // Helper to log data to MongoDB
    protected void logToDB(AnswerGenerationResponse llmResponse) {

        // New entity
        LLMEntity dbEntity = new LLMEntity();

        // Basic details
        dbEntity.setRequestId(llmResponse.getRequestId());
        dbEntity.setTime(llmResponse.getRequestDate());
        dbEntity.setService(model);

        // Generation details
        dbEntity.setGenerated(llmResponse.isGenerated());
        dbEntity.setGeneratedText(llmResponse.getText());
        dbEntity.setErrorMessage(llmResponse.getErrorMessage());

        // Save to DB
        llmEntityDao.insert(dbEntity);
    }


    // Helper function to get headers
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    // Auxiliary function to convert HTTP Response to desired response
    protected abstract AnswerGenerationResponse convertResponse(R response);

    // Auxiliary function to convert request to desired HTTP Request
    protected abstract Q convertRequest(AnswerGenerationRequest request);
}
