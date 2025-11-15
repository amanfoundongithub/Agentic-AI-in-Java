package com.ai.agent.llm.service;

import com.ai.agent.llm.model.request.LLMRequest;
import com.ai.agent.llm.model.response.LLMResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public abstract class LLMAbstractService<REQ extends LLMRequest, HTTP_RES> implements LLMService<REQ>{

    protected String url;
    protected Class<HTTP_RES> responseClass;

    protected abstract LLMResponse convert(HTTP_RES response);


    public LLMResponse generate(REQ request) {

        // Initialize a template to send the request
        RestTemplate template = new RestTemplate();

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Entity with the body
        HttpEntity<REQ> httpEntity = new HttpEntity<>(request, headers);

        // Send the POST request
        ResponseEntity<HTTP_RES> responseEntity = template.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                responseClass
        );


        // Return the content
        return convert(responseEntity.getBody());
    }
}
