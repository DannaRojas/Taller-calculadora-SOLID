package com.example.solid.o;

import com.example.solid.s.Operando;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PotenciaTest {

    @Test
    public void alEjecutarPotencia_debeRetornarElResultadoCorrecto() {
        Operacion potencia = new Potencia();
        Operando base = new Operando(2);
        Operando exponente = new Operando(3);
        
        Operando resultado = potencia.ejecutar(base, exponente);
        
        assertEquals(8, resultado.getValor());
    }
}
