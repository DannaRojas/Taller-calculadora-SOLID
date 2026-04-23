package com.example.solid.d;

import java.util.Scanner;

public final class ConsolaEntrada implements EntradaDatos {
    private final Scanner scanner;

    public ConsolaEntrada() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String leer() {
        return this.scanner.next();
    }
}
