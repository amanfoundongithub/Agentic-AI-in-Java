package com.ai.agent.repository.dao;

import com.ai.agent.repository.document.LLMEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LLMEntityDao extends MongoRepository<LLMEntity, String> {

}
