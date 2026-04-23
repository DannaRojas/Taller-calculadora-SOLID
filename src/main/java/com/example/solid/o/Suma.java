package com.example.solid.o;

import com.example.solid.s.Operando;

public final class Suma implements Operacion {
    @Override
    public Operando ejecutar(Operando a, Operando b) {
        int resultado = a.getValor() + b.getValor();
        return new Operando(resultado);
    }
}
