package com.ai.agent.llm;

import com.ai.agent.exception.LLMNotFoundException;
import com.ai.agent.llm.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LLMContext {

    private final Map<String, LLMService> llmServiceMap = new HashMap<>();

    @Autowired
    public LLMContext(List<LLMService> llmServices) {
        for(LLMService service : llmServices) {
            llmServiceMap.put(service.model(), service);
        }
    }


    public LLMService get(String model) {
        LLMService service = llmServiceMap.get(model);

        if(service == null) {
            throw new LLMNotFoundException(model);
        } else {
            return service;
        }
    }
}
