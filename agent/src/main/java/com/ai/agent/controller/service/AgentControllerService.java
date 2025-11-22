package com.ai.agent.controller.service;


import com.ai.agent.controller.dto.request.AgentRequest;
import com.ai.agent.controller.dto.response.JobStatusResponse;
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

    public AgentRequestSubmitted submitRequest(AgentRequest request) {
        try {
            String requestId = jobAllocator.allocate(new AnswerGenerationRequestMapper(request), request.model());
            return new AgentRequestSubmitted(requestId, Instant.now(), JobStatus.QUEUED);
        } catch (Exception e) {
            return new AgentRequestSubmitted(request.requestId(), Instant.now(), JobStatus.ERROR);
        }
    }

    public JobStatusResponse getResponse(String taskId) {
        try {
            Object response = db.get(taskId);

            if(response == null) {
                return new JobStatusResponse(taskId, JobStatus.PROCESSING, response);
            } else {
                return new JobStatusResponse(taskId, JobStatus.COMPLETED, response);
            }
        } catch (Exception e) {
            return new JobStatusResponse(taskId, JobStatus.ERROR, e.getMessage());
        }
    }
}
