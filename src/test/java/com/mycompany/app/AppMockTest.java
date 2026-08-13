package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for App with a mocked GreetingService.
 */
@ExtendWith(MockitoExtension.class)
public class AppMockTest {

    @Mock
    private GreetingService greetingService;

    @Test
    public void greetDelegatesToService() {
        when(greetingService.greetingFor("Bogdan")).thenReturn("Salut Bogdan!");

        App app = new App(greetingService);

        assertEquals("Salut Bogdan!", app.greet("Bogdan"));
        verify(greetingService, times(1)).greetingFor("Bogdan");
    }

    @Test
    public void greetReturnsWhateverTheServiceSays() {
        when(greetingService.greetingFor(anyString())).thenReturn("mocked");

        App app = new App(greetingService);

        assertEquals("mocked", app.greet("anything"));
    }

    @Test
    public void getMessageDoesNotTouchTheService() {
        App app = new App(greetingService);

        assertEquals("Hello World!", app.getMessage());
        verify(greetingService, never()).greetingFor(anyString());
    }
}
