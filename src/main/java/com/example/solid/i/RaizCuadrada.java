package com.example.solid.i;

import com.example.solid.s.Operando;

public final class RaizCuadrada implements Operacion, OperacionUnaria {
    private Operando a;

    @Override
    public void setOperando(Operando a) {
        this.a = a;
    }

    @Override
    public Operando ejecutar() {
        if (this.a == null) {
            throw new IllegalStateException("Operando no configurado");
        }
        this.a.validarParaRaiz();
        int resultado = (int) Math.sqrt(this.a.getValor());
        return new Operando(resultado);
    }
}
