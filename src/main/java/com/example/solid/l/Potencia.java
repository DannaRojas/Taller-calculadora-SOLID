package com.example.solid.l;

import com.example.solid.s.Operando;

public final class Potencia implements Operacion {
    private final Operando base;
    private final Operando exponente;

    public Potencia(Operando base, Operando exponente) {
        this.base = base;
        this.exponente = exponente;
    }

    @Override
    public Operando ejecutar() {
        int resultado = (int) Math.pow(this.base.getValor(), this.exponente.getValor());
        return new Operando(resultado);
    }
}
