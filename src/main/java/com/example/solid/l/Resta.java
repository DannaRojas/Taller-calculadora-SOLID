package com.example.solid.l;

import com.example.solid.s.Operando;

public final class Resta implements Operacion {
    private final Operando a;
    private final Operando b;

    public Resta(Operando a, Operando b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Operando ejecutar() {
        return new Operando(this.a.getValor() - this.b.getValor());
    }
}
