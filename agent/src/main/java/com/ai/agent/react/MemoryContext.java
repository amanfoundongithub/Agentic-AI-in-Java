package com.ai.agent.react;

import java.util.ArrayList;
import java.util.List;

public class MemoryContext {

    private final List<Memory> context = new ArrayList<>();

    public static class Memory {
        public final String role;
        public final String content;

        public Memory(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

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
            builder.append(m.role).append(": ").append(m.content).append("\n");
        }
        return builder.toString();
    }


}
