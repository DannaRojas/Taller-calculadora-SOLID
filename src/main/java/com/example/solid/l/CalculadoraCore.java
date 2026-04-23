package com.example.solid.l;

import com.example.solid.s.Operando;

public final class CalculadoraCore {
    public Operando ejecutar(Operacion operacion) {
        return operacion.ejecutar();
    }
}
