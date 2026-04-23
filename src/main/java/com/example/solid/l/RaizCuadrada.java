package com.example.solid.l;

import com.example.solid.s.Operando;

public final class RaizCuadrada implements Operacion {
    private final Operando a;

    public RaizCuadrada(Operando a) {
        this.a = a;
    }

    @Override
    public Operando ejecutar() {
        this.a.validarParaRaiz();
        int resultado = (int) Math.sqrt(this.a.getValor());
        return new Operando(resultado);
    }
}
