package com.ai.agent.core.job;


import com.ai.agent.core.api.AnswerGenerationRequest;
import com.ai.agent.core.api.AnswerGenerationResponse;
import com.ai.agent.core.db.ResultDB;
import com.ai.agent.react.ReactAgent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class AgentJobAllocator {

    private final TaskExecutor taskExecutor;
    private final ReactAgent agent;
    private final ResultDB resultDB;

    public AgentJobAllocator(
            @Qualifier("agentExec") TaskExecutor taskExecutor,
            ReactAgent agent,
            ResultDB resultDB
    ) {
        this.taskExecutor = taskExecutor;
        this.agent = agent;
        this.resultDB = resultDB;
    }

    public String allocate(AnswerGenerationRequest request, String model) {

        taskExecutor.execute(() -> {
            AnswerGenerationResponse finalResponse;
            try {
                finalResponse = agent.generate(request, model);
            } catch (Exception ex) {
                finalResponse = new AnswerGenerationResponse();
                finalResponse.setRequestId(request.getRequestId());
                finalResponse.setGenerated(false);
                finalResponse.setErrorMessage("Worker error: " + ex.getMessage());
            }

            resultDB.save(request.getRequestId(), finalResponse);
        });

        return request.getRequestId();
    }
}
