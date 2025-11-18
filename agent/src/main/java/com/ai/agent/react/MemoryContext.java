package com.ai.agent.react;

import java.util.ArrayList;
import java.util.List;


/**
 * A class containing memory context relevant for the LLM to
 * document the history of the conversation.
 *
 * @author amanfoundongithub
 */
public class MemoryContext {

    public record Memory(String role, String content) {
        // Record class for the memory unit of the LLM
    }

    private final List<Memory> context = new ArrayList<>();

    public void add(String role, String content){
        this.context.add(new Memory(role, content));
    }

    public List<Memory> getContext() {
        return this.context;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Memory m : this.context) {
            builder.append(m.role()).append(": ").append(m.content()).append("\n");
        }
        return builder.toString();
    }
}
