package com.ai.agent.tools;

import com.ai.agent.tools.dto.query.ToolQuery;
import com.ai.agent.tools.dto.result.ToolResult;

public interface Tool<R extends ToolResult, Q extends ToolQuery>{

    /**
     * A common interface to execute the action of a tool
     *
     * @param query The entity that contains the requested action items for the tool
     * @return Response from the tool
     */
    R execute(Q query);

}
