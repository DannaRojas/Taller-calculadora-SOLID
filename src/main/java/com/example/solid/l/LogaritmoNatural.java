package com.example.solid.l;

import com.example.solid.s.Operando;

public final class LogaritmoNatural implements OperacionUnaria {
    @Override
    public Operando ejecutar(Operando a) {
        a.validarParaLogaritmo();
        int resultado = (int) Math.log(a.getValor());
        return new Operando(resultado);
    }
}
