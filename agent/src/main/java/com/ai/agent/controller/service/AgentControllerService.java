package com.ai.agent.controller.service;


import com.ai.agent.controller.dto.request.AnswerRequestDTO;
import com.ai.agent.controller.dto.response.StatusQueryResponse;
import com.ai.agent.controller.dto.response.AgentRequestSubmitted;
import com.ai.agent.controller.mapper.AnswerGenerationRequestMapper;
import com.ai.agent.core.db.ResultDB;
import com.ai.agent.core.enums.JobStatus;
import com.ai.agent.core.job.AgentJobAllocator;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AgentControllerService {

    private final AgentJobAllocator jobAllocator;
    private final ResultDB db;

    public AgentControllerService(AgentJobAllocator jobAllocator, ResultDB db) {
        this.jobAllocator = jobAllocator;
        this.db = db;
    }

    public AgentRequestSubmitted submitRequest(AnswerRequestDTO request) {
        try {
            String requestId = jobAllocator.allocate(new AnswerGenerationRequestMapper(request), request.model());
            return new AgentRequestSubmitted(requestId, Instant.now(), JobStatus.QUEUED);
        } catch (Exception e) {
            return new AgentRequestSubmitted(request.requestId(), Instant.now(), JobStatus.ERROR);
        }
    }

    public StatusQueryResponse getResponse(String taskId) {
        try {
            Object response = db.get(taskId);

            if(response == null) {
                return new StatusQueryResponse(taskId, JobStatus.PROCESSING, response);
            } else {
                return new StatusQueryResponse(taskId, JobStatus.COMPLETED, response);
            }
        } catch (Exception e) {
            return new StatusQueryResponse(taskId, JobStatus.ERROR, e.getMessage());
        }
    }
}
