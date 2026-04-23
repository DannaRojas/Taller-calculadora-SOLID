package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalculadoraSolid {

    // =========================================================
    // S.O.L.I.D. Principles Applied:
    // S - Single Responsibility Principle: Cada clase de operación tiene un único propósito.
    // O - Open/Closed Principle: La clase Calculadora está abierta a la extensión (podemos agregar más operaciones) pero cerrada a la modificación.
    // L - Liskov Substitution Principle: Las implementaciones pueden sustituir a la interfaz sin romper el programa.
    // I - Interface Segregation Principle: Separamos operaciones de 1 argumento (unarias) y de 2 argumentos (binarias).
    // D - Dependency Inversion Principle: La Calculadora depende de abstracciones (interfaces), no de implementaciones concretas.
    // =========================================================

    // --- 1. Interfaces de Operación (Abstracciones) ---
    
    public interface OperacionBinaria {
        double ejecutar(int a, int b);
    }

    public interface OperacionUnaria {
        double ejecutar(int a);
    }

    // --- 2. Implementaciones de Operaciones Binarias ---

    public static class Suma implements OperacionBinaria {
        @Override
        public double ejecutar(int a, int b) {
            return (double) a + b;
        }
    }

    public static class Resta implements OperacionBinaria {
        @Override
        public double ejecutar(int a, int b) {
            return (double) a - b;
        }
    }

    public static class Multiplicacion implements OperacionBinaria {
        @Override
        public double ejecutar(int a, int b) {
            return (double) a * b;
        }
    }

    public static class Division implements OperacionBinaria {
        @Override
        public double ejecutar(int a, int b) {
            if (b == 0) {
                throw new ArithmeticException("No se puede dividir por cero");
            }
            return (double) a / b;
        }
    }

    // --- 3. Implementaciones de Operaciones Unarias ---

    public static class RaizCuadrada implements OperacionUnaria {
        @Override
        public double ejecutar(int a) {
            if (a < 0) {
                throw new ArithmeticException("La raíz cuadrada de un número negativo no es un número real");
            }
            return Math.sqrt(a);
        }
    }

    public static class LogaritmoNatural implements OperacionUnaria {
        @Override
        public double ejecutar(int a) {
            if (a <= 0) {
                throw new ArithmeticException("El logaritmo natural solo está definido para números mayores a cero");
            }
            return Math.log(a);
        }
    }

    // --- 4. Core de la Calculadora (Orquestador) ---

    public static class Calculadora {
        private final Map<String, OperacionBinaria> operacionesBinarias = new HashMap<>();
        private final Map<String, OperacionUnaria> operacionesUnarias = new HashMap<>();

        // Permite registrar nuevas operaciones sin cambiar esta clase (Open/Closed)
        public void registrarOperacionBinaria(String simbolo, OperacionBinaria operacion) {
            operacionesBinarias.put(simbolo, operacion);
        }

        public void registrarOperacionUnaria(String simbolo, OperacionUnaria operacion) {
            operacionesUnarias.put(simbolo, operacion);
        }

        public double calcularBinaria(String simbolo, int a, int b) {
            OperacionBinaria op = operacionesBinarias.get(simbolo);
            if (op == null) throw new UnsupportedOperationException("Operación binaria no soportada: " + simbolo);
            return op.ejecutar(a, b);
        }

        public double calcularUnaria(String simbolo, int a) {
            OperacionUnaria op = operacionesUnarias.get(simbolo);
            if (op == null) throw new UnsupportedOperationException("Operación unaria no soportada: " + simbolo);
            return op.ejecutar(a);
        }
        
        public boolean esOperacionBinaria(String simbolo) {
            return operacionesBinarias.containsKey(simbolo);
        }

        public boolean esOperacionUnaria(String simbolo) {
            return operacionesUnarias.containsKey(simbolo);
        }
    }

    // --- 5. Interfaz de Consola y Configuración Principal ---

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        
        // Inyección de dependencias / Configuración (Se cumple Open/Closed)
        calc.registrarOperacionBinaria("+", new Suma());
        calc.registrarOperacionBinaria("-", new Resta());
        calc.registrarOperacionBinaria("*", new Multiplicacion());
        calc.registrarOperacionBinaria("/", new Division());
        calc.registrarOperacionUnaria("sqrt", new RaizCuadrada());
        calc.registrarOperacionUnaria("ln", new LogaritmoNatural());

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Calculadora SOLID ===");
        System.out.println("Operaciones Binarias: +, -, *, /");
        System.out.println("Operaciones Unarias: sqrt, ln");
        System.out.println("Escriba 'salir' para terminar.");

        while (true) {
            System.out.print("\nIngrese la operación (+, -, *, /, sqrt, ln) o 'salir': ");
            String op = scanner.next();

            if (op.equalsIgnoreCase("salir")) {
                break;
            }

            try {
                if (calc.esOperacionBinaria(op)) {
                    System.out.print("Ingrese el primer número entero: ");
                    int a = scanner.nextInt();
                    System.out.print("Ingrese el segundo número entero: ");
                    int b = scanner.nextInt();
                    
                    double resultado = calc.calcularBinaria(op, a, b);
                    System.out.println("Resultado: " + resultado);
                    
                } else if (calc.esOperacionUnaria(op)) {
                    System.out.print("Ingrese el número entero: ");
                    int a = scanner.nextInt();
                    
                    double resultado = calc.calcularUnaria(op, a);
                    System.out.println("Resultado: " + resultado);
                    
                } else {
                    System.out.println("Operación no reconocida. Intente nuevamente.");
                }
            } catch (ArithmeticException | UnsupportedOperationException e) {
                System.out.println("Error matemático/operación: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Asegúrese de ingresar números enteros.");
                scanner.nextLine(); // Limpiar el buffer en caso de error de Scanner
            }
        }
        
        scanner.close();
        System.out.println("Calculadora terminada.");
    }
}
