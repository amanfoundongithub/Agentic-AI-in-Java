package com.ai.agent.repository.document;

import com.ai.agent.mcp.tool_query.ToolQuery;
import com.ai.agent.mcp.tool_result.ToolResult;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "ToolEntity")
public class ToolEntity {

    @Id
    private String id;

    @Field(name = "referenceId")
    private String referenceId;

    @Field(name = "called_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field(name = "session_called_by")
    private String sessionId;

    @Field(name = "name")
    private String name;

    @Field(name = "query")
    private ToolQuery query;

    @Field(name = "status")
    private String status;

    @Field(name = "result")
    private ToolResult result;

    // ---- Getters and Setters ----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ToolQuery getQuery() {
        return query;
    }

    public void setQuery(ToolQuery query) {
        this.query = query;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ToolResult getResult() {
        return result;
    }

    public void setResult(ToolResult result) {
        this.result = result;
    }
}
