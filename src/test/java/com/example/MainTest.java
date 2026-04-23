package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    
    @Test
    public void testSumar() {
        Main main = new Main();
        assertEquals(5, main.sumar(2, 3), "2 + 3 debe ser igual a 5");
    }
}
