package com.mycompany.app;

/**
 * Source of greeting messages. Kept as an interface so it can be mocked in tests.
 */
public interface GreetingService {

    String greetingFor(String name);
}
