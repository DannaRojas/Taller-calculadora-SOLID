package com.example.solid.l;

import com.example.solid.s.Operando;

public final class RaizCuadrada implements OperacionUnaria {
    @Override
    public Operando ejecutar(Operando a) {
        a.validarParaRaiz();
        int resultado = (int) Math.sqrt(a.getValor());
        return new Operando(resultado);
    }
}
