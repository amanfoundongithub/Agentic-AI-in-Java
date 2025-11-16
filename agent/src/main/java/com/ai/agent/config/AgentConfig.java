package com.ai.agent.config;


/**
 * A utility class to store the configurations of the agent
 * as well as utility constants related to answer generation by ReACT agent.
 *
 * @author amanfoundongithub
 */
public class AgentConfig {

    // Maximum number of iterations in the agent's loop
    public static final int MAX_ITERATIONS = 10;

    // Names of all the tags for the agent
    public static final String FINAL_TAG   = "final";
    public static final String ACTION_TAG  = "action";
    public static final String THOUGHT_TAG = "thought";

    // Roles for the context
    public static final String USER_PROMPT      = "user";
    public static final String SYSTEM_PROMPT    = "system";
    public static final String GENERATED_ANSWER = "memory";
    public static final String TOOL_ACTION      = "tool";

    private AgentConfig() {
        // Removes default public constructor
    }

}
