package com.ai.agent.tools;

public interface Tool<RES, REQ>{

    RES execute(REQ request);

}
