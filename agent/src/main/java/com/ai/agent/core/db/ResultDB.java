package com.ai.agent.core.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ResultDB {

    private final RedisTemplate<String, Object> redis;

    @Autowired
    public ResultDB(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    public void save(String id, Object response) {
        redis.opsForValue().set(id, response);
    }

    public Object get(String id) {
        return redis.opsForValue().get(id);
    }
}
