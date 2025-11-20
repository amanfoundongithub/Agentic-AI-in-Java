package com.ai.agent.mcp.tool;

import com.ai.agent.mcp.tool.query.ToolQuery;
import com.ai.agent.mcp.tool.result.ToolResult;
import com.ai.agent.persistence.dao.ToolEntityDao;
import com.ai.agent.persistence.entity.ToolEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

public abstract class ToolAbstract<R extends ToolResult, Q extends ToolQuery> implements Tool<R, Q>{

    @Autowired
    protected ToolEntityDao toolDao;

    @Autowired
    protected WebClient webClient;

    protected static final Logger LOGGER = LoggerFactory.getLogger(ToolAbstract.class);

    protected String name;
    protected String description;
    protected Class<Q> queryClass;

    protected abstract R processingLogic(Q query) throws Exception;
    protected abstract R processingError();

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Class<Q> queryClass() {
        return queryClass;
    }

    @Override
    public R execute(Q query, String sessionId) {

        // Log that we received the request
        LOGGER.info("\tINFO: Request for {} by sessionId: {} Received", name, sessionId);

        // Reference ID
        String referenceId = UUID.randomUUID().toString();
        LOGGER.info("\tINFO: Starting tool...(RefId: {})", referenceId);

        // MongoDB entity helper
        ToolEntity entity = new ToolEntity();
        entity.setSessionId(sessionId);
        entity.setReferenceId(referenceId);
        entity.setName(name);
        entity.setQuery(query);

        // Result
        R result;

        try {
            result = processingLogic(query);

            entity.setStatus("SUCCESS");
            entity.setResult(result);

        } catch (Exception e) {
            LOGGER.error("\tERROR: {}", e.getMessage());
            result = processingError();

            entity.setStatus("FAILURE");
            entity.setErrorMessage(e.getMessage());

        } finally {
            LOGGER.info("\tINFO: Request for {} by sessionId: {} Completed", name, sessionId);
            toolDao.insert(entity);
        }

        return result;
    }

}
