package com.example.solid.d;

import com.example.solid.i.Suma;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DipTest {

    // 1. Fakes (Implementaciones de prueba para invertir la dependencia)
    private static final class FakeEntradaDatos implements EntradaDatos {
        private final String[] entradas;
        private int indice = 0;

        public FakeEntradaDatos(String... entradas) {
            this.entradas = entradas;
        }

        @Override
        public String leer() {
            if (this.indice < this.entradas.length) {
                return this.entradas[this.indice++];
            }
            return "salir";
        }
    }

    private static final class FakeSalidaDatos implements SalidaDatos {
        public final List<String> mensajes = new ArrayList<>();

        @Override
        public void imprimir(String mensaje) {
            this.mensajes.add(mensaje);
        }
    }

    @Test
    public void alInyectarFakes_elOrquestadorPuedeFuncionar_sinDependerDeLaConsolaReal() {
        // Arrange
        // Simulamos que el usuario teclea el primer número "10" y el segundo "5"
        FakeEntradaDatos entradaMock = new FakeEntradaDatos("10", "5");
        FakeSalidaDatos salidaMock = new FakeSalidaDatos();

        // Se inyectan las dependencias en la aplicación (Dependency Injection)
        CalculadoraCore core = new CalculadoraCore();
        // El core recibe la operación inyectada
        core.registrarOperacion("+", new Suma()); 
        
        // El orquestador recibe el core, la entrada y la salida inyectados
        Orquestador orquestador = new Orquestador(entradaMock, salidaMock, core);

        // Act
        // Simulamos que elige el menú "+" y corre la operación
        orquestador.procesarCiclo("+");

        // Assert
        // El resultado debe ser 15 y haberse enviado a SalidaDatos, NO a System.out
        assertTrue(salidaMock.mensajes.contains("Resultado: 15"));
    }
}
