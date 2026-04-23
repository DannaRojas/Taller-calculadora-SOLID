package com.example.solid.i;

import com.example.solid.s.Operando;

public final class Suma implements Operacion, OperacionBinaria {
    private Operando a;
    private Operando b;

    @Override
    public void setOperandos(Operando a, Operando b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Operando ejecutar() {
        if (this.a == null || this.b == null) {
            throw new IllegalStateException("Operandos no configurados");
        }
        return new Operando(this.a.getValor() + this.b.getValor());
    }
}
