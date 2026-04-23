package com.example.solid.s;

public final class Operando {
    private final int valor;

    public Operando(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return this.valor;
    }

    public void validarParaDivision() {
        if (this.valor == 0) {
            throw new IllegalArgumentException("No se puede dividir por cero.");
        }
    }

    public void validarParaRaiz() {
        if (this.valor < 0) {
            throw new IllegalArgumentException("No se pueden calcular raíces de números negativos.");
        }
    }

    public void validarParaLogaritmo() {
        if (this.valor <= 0) {
            throw new IllegalArgumentException("El logaritmo solo está definido para números mayores a cero.");
        }
    }
}
