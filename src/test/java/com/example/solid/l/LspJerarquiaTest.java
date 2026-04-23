package com.example.solid.l;

import com.example.solid.s.Operando;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LspJerarquiaTest {

    @Test
    public void alEjecutarOperacionBinaria_debeRespetarElContrato() {
        CalculadoraCore calculadora = new CalculadoraCore();
        OperacionBinaria suma = new Suma();
        Operando a = new Operando(10);
        Operando b = new Operando(5);
        
        Operando resultado = calculadora.ejecutarBinaria(suma, a, b);
        assertEquals(15, resultado.getValor());
    }

    @Test
    public void alEjecutarOperacionUnaria_debeRespetarElContrato() {
        CalculadoraCore calculadora = new CalculadoraCore();
        // Anonymous class just for test, could be RaizCuadrada
        OperacionUnaria doble = new OperacionUnaria() {
            @Override
            public Operando ejecutar(Operando a) {
                return new Operando(a.getValor() * 2);
            }
        };
        Operando a = new Operando(10);
        
        Operando resultado = calculadora.ejecutarUnaria(doble, a);
        assertEquals(20, resultado.getValor());
    }
}
