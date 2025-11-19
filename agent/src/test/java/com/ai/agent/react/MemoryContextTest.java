package com.ai.agent.react;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class MemoryContextTest {

    @Test
    void testMemoryContext() {

        // Define a new instance
        MemoryContext memoryContext = new MemoryContext();

        // Add samples to check
        memoryContext.add("user", "hello this is message 1");
        assertEquals(1, memoryContext.getContext().size());
        assertEquals("user", memoryContext.getContext().getLast().role());

        // Add some more sample
        memoryContext.add("system", "this is system generated message 2");
        assertEquals(2, memoryContext.getContext().size());
        assertEquals("this is system generated message 2", memoryContext.getContext().getLast().content());

        // Test Completed!
    }


}
