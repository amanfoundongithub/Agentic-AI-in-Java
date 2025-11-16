package com.ai.agent.llm;

import com.ai.agent.llm.model.LLMRequest;
import com.ai.agent.llm.model.LLMResponse;
import com.ai.agent.llm.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LLMContext {

    Map<String, LLMService> llmServiceMap = new HashMap<>();

    @Autowired
    public LLMContext(List<LLMService> llmServices) {
        for(LLMService service : llmServices) {
            llmServiceMap.put(service.model(), service);
        }
    }

    public LLMService get(String model) {
        return llmServiceMap.get(model);
    }


    public LLMResponse generate(String model,LLMRequest query) {
        LLMService llmService = llmServiceMap.get(model);

        if(llmService == null) {
            throw new IllegalArgumentException("No LLM Service found with the name: " + model);
        } else {
            return llmService.generate(query);
        }

    }
}
