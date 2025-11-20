package com.ai.agent.repository.dao;

import com.ai.agent.repository.document.ToolEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToolEntityDao extends MongoRepository<ToolEntity, String> {

}
