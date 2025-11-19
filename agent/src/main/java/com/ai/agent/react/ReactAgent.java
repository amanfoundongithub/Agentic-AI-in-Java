package com.ai.agent.react;

import com.ai.agent.config.AgentConfig;
import com.ai.agent.exception.MaxAttemptsReachedException;
import com.ai.agent.exception.UserPromptNotPresentException;
import com.ai.agent.llm.LLMContext;
import com.ai.agent.llm.dto.LLMRequest;
import com.ai.agent.llm.dto.LLMResponse;
import com.ai.agent.llm.service.LLMService;
import com.ai.agent.mcp.MCPServer;
import com.ai.agent.mcp.tool.Tool;
import com.ai.agent.mcp.tool_query.ToolQuery;
import com.ai.agent.mcp.tool_result.ToolResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReactAgent {

    // Object Mapper for Jackson
    private static final ObjectMapper mapper = new ObjectMapper();

    // Logger for the Agent
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactAgent.class);

    // MCP Server Injection
    private final MCPServer  mcpServer;

    // LLM Server Injection
    private final LLMContext llmContext;

    public ReactAgent(MCPServer mcpServer, LLMContext llmContext) {
        this.mcpServer = mcpServer;
        this.llmContext = llmContext;
    }


    public LLMResponse generate(LLMRequest request, String model) {

        // Request is received!
        LOGGER.info("INFO: Request with requestId {} for agent (model = {}) RECEIVED", request.getRequestId(), model);

        // Logging In Successfully!
        LOGGER.info("INFO: Starting Generation Below...\n");

        // Create an LLM Response for the Agent and populate default values
        LLMResponse finalResponse = new LLMResponse();
        finalResponse.setRequestId(request.getRequestId());
        finalResponse.setGenerated(false);

        // Get a ReACT prompt for system
        String sysReactPrompt = createSystemPrompt();

        // Create an LLM memory context to store relevant information
        MemoryContext memory = new MemoryContext();

        try {

            // Fetch the service, throws exception if not there
            LLMService service = llmContext.get(model);

            // User Prompt Addition (error if not there)
            String userPrompt = request.getPrompt();
            if(userPrompt != null) {
                memory.add(AgentConfig.USER_PROMPT, userPrompt);
            } else {
                throw new UserPromptNotPresentException();
            }

            // System Prompt Addition (if exists, otherwise fine)
            String sysPrompt  = request.getSystemPrompt();
            if(sysPrompt != null) {
                memory.add(AgentConfig.SYSTEM_PROMPT, sysPrompt);
            }

            // Iterate over the agent's iterations for reasoning
            for(int i = 0; i < AgentConfig.MAX_ITERATIONS ; i++) {

                // Create a prompt based on context
                String reactPrompt = memory.toString();

                // Create a new request for the LLM with parameters
                LLMRequest newRequest = new LLMRequest(reactPrompt, sysReactPrompt);

                // Call service to generate response
                LLMResponse newResponse = service.generate(newRequest);

                // Get text, if it is not there, it means LLM failed to generate so retry again
                String text = newResponse.getText();
                if(text == null) {
                    continue;
                }

                memory.add(AgentConfig.GENERATED_ANSWER, text);

                // The tag for the final answer?
                if(text.contains("</" + AgentConfig.FINAL_TAG + ">")) {

                    // Get the final answer
                    String finalAnswer = AgentConfig.extractTextBetweenTags(text, AgentConfig.FINAL_TAG);

                    // Set the fields of the response
                    finalResponse.setGenerated(true);
                    finalResponse.setText(finalAnswer);

                    return finalResponse;

                } else if(text.contains("</" + AgentConfig.ACTION_TAG + ">")) {

                    // Extract the action item and the query
                    String actionItem = AgentConfig.getActionNameFromText(text);
                    String jsonQuery  = AgentConfig.getActionJSONFromText(text);

                    // Try to get response from the tool
                    try {

                        // Fetch tool from MCP Server and the class
                        Tool<?,?> tool = mcpServer.getTool(actionItem);
                        Class<? extends ToolQuery> queryClass = tool.queryClass();

                        // Run the tool to fetch the result
                        ToolQuery queryObj = mapper.readValue(jsonQuery, queryClass);
                        ToolResult toolResult = ((Tool<ToolResult, ToolQuery>) tool).execute(queryObj);

                        // Now add this result to the memory
                        String mappedStr = mapper.writeValueAsString(toolResult);
                        memory.add(AgentConfig.TOOL_ACTION, mappedStr);

                    } catch (Exception e) {

                        // Error in tool
                        memory.add(AgentConfig.TOOL_ACTION, "ERROR: " + e.getMessage());
                    }
                }


            }

            // LLM has exceeded the number of attempts so it makes sense to throw an error here
            throw new MaxAttemptsReachedException(AgentConfig.MAX_ITERATIONS);

        }
        catch (Exception e) {
            LOGGER.error("ERROR: {}", e.getMessage());
            finalResponse.setErrorMessage(e.getMessage());
        } finally {
            LOGGER.info("INFO: Request with requestId {} for agent (model = {}) COMPLETED", request.getRequestId(), model);
        }

        return finalResponse;
    }


    private String createSystemPrompt(){
        String availableTools = mcpServer.showAllAvailableTools();

        return """
            You are an expert reasoning agent. You are provided some tools and you need to think step-by-step with
            reasoning to arrive at the correct answer. When you are done, you are supposed to
            give the final answer cleanly.
            
            Follow these rules strictly:
            1. Your chain-of-thought must be inside these tags:
                <thought> ... </thought>
            2. When you want to call a tool, respond ONLY in this exact XML-style format:
                <action name="<tool_name>">
                {
                    "field1": "...",
                    "field2": 123,
                    ...
                }
               </action>
            3. The JSON inside <action> MUST follow the exact input_format of the tool.
            4. After a tool result is returned to you, continue reasoning in <thought> tags.
            5. Only after getting all information, when you are ready to answer, write your answer as follows:
                <final> {your_answer_here} </final>
            6. DO NOT return anything outside <thought>, <action>, or <final> tags.
            Remember, NO Markdown. NO explanations. NO natural conversation outside these tags.
            ==========================""" + availableTools + "==========================\n" +
                "Use these tools when needed. If not needed, go to <final>.\n";
    }




}
