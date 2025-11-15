package com.ai.agent.tools;

public interface Tool<RES, REQ>{

    /**
     * A common interface to execute the action of a tool
     *
     * @param request The entity that contains the requested action items for the tool
     * @return Response from the tool
     */
    RES execute(REQ request);

}
