package com.example.solid.o;

import com.example.solid.s.Operando;

public final class CalculadoraCore {
    public Operando ejecutar(Operacion operacion, Operando a, Operando b) {
        return operacion.ejecutar(a, b);
    }
}
