package com.example.solid.l;

import com.example.solid.s.Operando;

public final class LogaritmoNatural implements Operacion {
    private final Operando a;

    public LogaritmoNatural(Operando a) {
        this.a = a;
    }

    @Override
    public Operando ejecutar() {
        this.a.validarParaLogaritmo();
        int resultado = (int) Math.log(this.a.getValor());
        return new Operando(resultado);
    }
}
