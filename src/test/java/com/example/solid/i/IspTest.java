package com.example.solid.i;

import com.example.solid.s.Operando;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IspTest {

    @Test
    public void alUsarOperacionBinaria_soloImplementaLosMetodosQueRealmenteUsa() {
        Suma suma = new Suma();
        
        // Polimorfismo basado en la interfaz segregada de inyección
        OperacionBinaria opBinaria = suma;
        opBinaria.setOperandos(new Operando(10), new Operando(5));
        
        // Polimorfismo basado en la interfaz segregada de ejecución
        Operacion ejecutable = suma;
        Operando resultado = ejecutable.ejecutar();
        
        assertEquals(15, resultado.getValor());
    }

    @Test
    public void alUsarOperacionUnaria_soloImplementaLosMetodosQueRealmenteUsa() {
        RaizCuadrada raiz = new RaizCuadrada();
        
        // Polimorfismo basado en la interfaz segregada de inyección
        OperacionUnaria opUnaria = raiz;
        opUnaria.setOperando(new Operando(16));
        
        // Polimorfismo basado en la interfaz segregada de ejecución
        Operacion ejecutable = raiz;
        Operando resultado = ejecutable.ejecutar();
        
        assertEquals(4, resultado.getValor());
    }
}
