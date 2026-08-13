package com.mycompany.app;

/**
 * Hello world!
 */
public class App {

    private static final String MESSAGE = "Hello World!";

    private final GreetingService greetingService;

    public App() {
        this(new DefaultGreetingService());
    }

    public App(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public static void main(String[] args) {
        App app = new App();
        String name = args.length > 0 ? args[0] : null;
        System.out.println(app.greet(name));
    }

    public String getMessage() {
        return MESSAGE;
    }

    public String greet(String name) {
        return greetingService.greetingFor(name);
    }
}
