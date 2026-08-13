package com.mycompany.app;

/**
 * Default, real implementation used when the application runs.
 */
public class DefaultGreetingService implements GreetingService {

    @Override
    public String greetingFor(String name) {
        if (name == null || name.isBlank()) {
            return "Hello World!";
        }
        return "Hello " + name + "!";
    }
}
