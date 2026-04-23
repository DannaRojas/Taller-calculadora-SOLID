package com.example.solid.l;

import com.example.solid.s.Operando;

public final class Suma implements OperacionBinaria {
    @Override
    public Operando ejecutar(Operando a, Operando b) {
        int resultado = a.getValor() + b.getValor();
        return new Operando(resultado);
    }
}
