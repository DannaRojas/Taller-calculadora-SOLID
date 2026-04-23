package com.example.solid.o;

import com.example.solid.s.Operando;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OperacionTest {

    @Test
    public void alEjecutarSuma_debeRetornarLaSumaCorrecta() {
        Operacion suma = new Suma();
        Operando a = new Operando(5);
        Operando b = new Operando(3);
        
        Operando resultado = suma.ejecutar(a, b);
        
        assertEquals(8, resultado.getValor());
    }

    @Test
    public void alEjecutarResta_debeRetornarLaRestaCorrecta() {
        Operacion resta = new Resta();
        Operando a = new Operando(10);
        Operando b = new Operando(4);
        
        Operando resultado = resta.ejecutar(a, b);
        
        assertEquals(6, resultado.getValor());
    }
}
