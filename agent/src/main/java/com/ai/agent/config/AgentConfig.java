package com.ai.agent.config;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // Helper to extract text between tags
    public static String extractTextBetweenTags(String text, String tag) {
        String open = "<" + tag + ">";
        String close = "</" + tag + ">";
        int start = text.indexOf(open);
        int end = text.indexOf(close);

        if (start == -1 || end == -1) return "";
        return text.substring(start + open.length(), end).trim();
    }

    // Helper to get action name
    public static String getActionNameFromText(String text) {
        Pattern p = Pattern.compile(
                "<action\\s+name=\\\"(.*?)\\\">",
                Pattern.MULTILINE
        );
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }

    public static String getActionJSONFromText(String text) {
        Pattern p = Pattern.compile(
                "<action\\s+name=\\\"(.*?)\\\">([\\s\\S]*?)</action>",
                Pattern.MULTILINE
        );
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(2).trim();   // return ONLY JSON
        } else {
            return null;
        }
    }

    private AgentConfig() {
        // Removes default public constructor
    }

}
