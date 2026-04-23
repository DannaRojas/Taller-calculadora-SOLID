package com.example.solid.o;

import com.example.solid.s.Operando;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraCoreTest {

    @Test
    public void alEjecutarOperacionConCalculadora_debeRetornarElResultadoCorrecto() {
        CalculadoraCore calculadora = new CalculadoraCore();
        Operacion suma = new Suma();
        Operando a = new Operando(5);
        Operando b = new Operando(3);
        
        Operando resultado = calculadora.ejecutar(suma, a, b);
        
        assertEquals(8, resultado.getValor());
    }

    @Test
    public void alEjecutarPotenciaConCalculadora_debeRetornarElResultadoCorrectoSinModificarCore() {
        CalculadoraCore calculadora = new CalculadoraCore();
        Operacion potencia = new Potencia();
        Operando a = new Operando(2);
        Operando b = new Operando(3);
        
        Operando resultado = calculadora.ejecutar(potencia, a, b);
        
        assertEquals(8, resultado.getValor());
    }
}
