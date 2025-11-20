package com.ai.agent.persistence.dao;

import com.ai.agent.persistence.entity.ToolEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToolEntityDao extends MongoRepository<ToolEntity, String> {

}
