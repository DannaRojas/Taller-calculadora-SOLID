package com.example.solid.s;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OperandoTest {

    @Test
    public void alCrearOperando_debeAlmacenarElValorCorrectamente() {
        Operando operando = new Operando(5);
        assertEquals(5, operando.getValor());
    }

    @Test
    public void siEsCero_alValidarDivisor_debeLanzarExcepcion() {
        Operando operando = new Operando(0);
        assertThrows(IllegalArgumentException.class, () -> {
            operando.validarParaDivision();
        });
    }

    @Test
    public void siEsNegativo_alValidarRaiz_debeLanzarExcepcion() {
        Operando operando = new Operando(-1);
        assertThrows(IllegalArgumentException.class, () -> {
            operando.validarParaRaiz();
        });
    }
    
    @Test
    public void siEsCeroOMenor_alValidarLogaritmo_debeLanzarExcepcion() {
        Operando operando = new Operando(0);
        assertThrows(IllegalArgumentException.class, () -> {
            operando.validarParaLogaritmo();
        });
    }
}
