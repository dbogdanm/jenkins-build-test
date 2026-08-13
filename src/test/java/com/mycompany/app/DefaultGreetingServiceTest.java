package com.mycompany.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the real greeting service.
 */
public class DefaultGreetingServiceTest {

    private final GreetingService service = new DefaultGreetingService();

    @Test
    public void greetsByName() {
        assertEquals("Hello Bogdan!", service.greetingFor("Bogdan"));
    }

    @Test
    public void fallsBackWhenNameIsMissing() {
        assertEquals("Hello World!", service.greetingFor(null));
        assertEquals("Hello World!", service.greetingFor("   "));
    }
}
