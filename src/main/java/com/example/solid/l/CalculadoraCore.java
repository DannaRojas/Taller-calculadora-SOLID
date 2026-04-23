package com.example.solid.l;

import com.example.solid.s.Operando;

public final class CalculadoraCore {
    public Operando ejecutarBinaria(OperacionBinaria operacion, Operando a, Operando b) {
        return operacion.ejecutar(a, b);
    }

    public Operando ejecutarUnaria(OperacionUnaria operacion, Operando a) {
        return operacion.ejecutar(a);
    }
}
