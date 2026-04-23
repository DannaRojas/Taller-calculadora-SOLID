package com.example.solid.d;

import com.example.solid.i.Suma;
import com.example.solid.i.Resta;
import com.example.solid.i.RaizCuadrada;
import com.example.solid.i.LogaritmoNatural;

public final class Main {
    public static void main(String[] args) {
        // 1. Instanciar dependencias de bajo nivel
        EntradaDatos entrada = new ConsolaEntrada();
        SalidaDatos salida = new ConsolaSalida();

        // 2. Instanciar y configurar el Core
        CalculadoraCore core = new CalculadoraCore();
        core.registrarOperacion("+", new Suma());
        core.registrarOperacion("-", new Resta());
        core.registrarOperacion("sqrt", new RaizCuadrada());
        core.registrarOperacion("ln", new LogaritmoNatural());

        // 3. Inyectar dependencias en el alto nivel (Orquestador)
        Orquestador orquestador = new Orquestador(entrada, salida, core);

        // 4. Iniciar la aplicación
        orquestador.iniciar();
    }
}
