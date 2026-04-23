package com.example.solid.o;

import com.example.solid.s.Operando;

public final class Potencia implements Operacion {
    @Override
    public Operando ejecutar(Operando base, Operando exponente) {
        int resultado = (int) Math.pow(base.getValor(), exponente.getValor());
        return new Operando(resultado);
    }
}
