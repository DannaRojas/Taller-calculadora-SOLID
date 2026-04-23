package com.example.solid.d;

import com.example.solid.i.Operacion;
import com.example.solid.i.OperacionBinaria;
import com.example.solid.i.OperacionUnaria;
import com.example.solid.s.Operando;

public final class Orquestador {
    private final EntradaDatos entrada;
    private final SalidaDatos salida;
    private final CalculadoraCore core;

    public Orquestador(EntradaDatos entrada, SalidaDatos salida, CalculadoraCore core) {
        this.entrada = entrada;
        this.salida = salida;
        this.core = core;
    }

    public void procesarCiclo(String operacionSimbolo) {
        Operacion operacion = this.core.getOperacion(operacionSimbolo);
        if (operacion == null) {
            this.salida.imprimir("Operacion no soportada.");
            return;
        }

        try {
            if (operacion instanceof OperacionBinaria) {
                this.salida.imprimir("Ingrese el primer numero:");
                int v1 = Integer.parseInt(this.entrada.leer());
                this.salida.imprimir("Ingrese el segundo numero:");
                int v2 = Integer.parseInt(this.entrada.leer());
                OperacionBinaria opBinaria = (OperacionBinaria) operacion;
                opBinaria.setOperandos(new Operando(v1), new Operando(v2));
            } else if (operacion instanceof OperacionUnaria) {
                this.salida.imprimir("Ingrese el numero:");
                int v1 = Integer.parseInt(this.entrada.leer());
                OperacionUnaria opUnaria = (OperacionUnaria) operacion;
                opUnaria.setOperando(new Operando(v1));
            }

            Operando resultado = operacion.ejecutar();
            this.salida.imprimir("Resultado: " + resultado.getValor());
        } catch (NumberFormatException e) {
            this.salida.imprimir("Error: Entrada invalida.");
        } catch (Exception e) {
            this.salida.imprimir("Error matematico: " + e.getMessage());
        }
    }

    public void iniciar() {
        this.salida.imprimir("=== Calculadora SOLID DIP ===");
        while (true) {
            this.salida.imprimir("Ingrese operacion (+, -, *, /, sqrt, ln) o 'salir':");
            String op = this.entrada.leer();
            if ("salir".equalsIgnoreCase(op) || op == null) {
                break;
            }
            this.procesarCiclo(op);
        }
        this.salida.imprimir("Fin del programa.");
    }
}
