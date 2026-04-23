package com.example.solid.d;

public final class ConsolaSalida implements SalidaDatos {
    @Override
    public void imprimir(String mensaje) {
        System.out.println(mensaje);
    }
}
