package com.ai.agent.persistence.dao;

import com.ai.agent.persistence.entity.LLMEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LLMEntityDao extends MongoRepository<LLMEntity, String> {

}
