package com.example.solid.l;

import com.example.solid.s.Operando;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraLSPTest {

    @Test
    public void alIterarListaMixtaDeOperaciones_debeProcesarTodoSinInstanceofNiErrores() {
        // Fase de preparación: Las operaciones reciben sus operandos al instanciarse (Patrón Command)
        Operacion suma = new Suma(new Operando(10), new Operando(5));
        Operacion raiz = new RaizCuadrada(new Operando(25));
        
        List<Operacion> listaMixta = Arrays.asList(suma, raiz);
        List<Operando> resultados = new ArrayList<>();

        // Fase de ejecución: Polimorfismo puro, 100% sustituibles, sin saber si son unarias o binarias
        for (Operacion operacion : listaMixta) {
            resultados.add(operacion.ejecutar());
        }

        // Fase de aserción
        assertEquals(15, resultados.get(0).getValor());
        assertEquals(5, resultados.get(1).getValor());
    }
}
