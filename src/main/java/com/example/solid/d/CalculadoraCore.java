package com.example.solid.d;

import com.example.solid.i.Operacion;
import java.util.HashMap;
import java.util.Map;

public final class CalculadoraCore {
    private final Map<String, Operacion> operaciones = new HashMap<>();

    public void registrarOperacion(String simbolo, Operacion operacion) {
        this.operaciones.put(simbolo, operacion);
    }

    public Operacion getOperacion(String simbolo) {
        return this.operaciones.get(simbolo);
    }
}
