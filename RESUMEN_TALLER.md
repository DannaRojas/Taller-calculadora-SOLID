# Taller de Refactorización: Principios S.O.L.I.D. 

Este documento resume paso a paso el ejercicio de refactorización guiado por TDD, partiendo de una calculadora monolítica ("código espagueti") hasta llegar a una arquitectura limpia, modular y altamente testeable siguiendo los 5 principios SOLID.

---

## Estado Inicial: El Código Espagueti
Al inicio, teníamos una única clase `CalculadoraSolid` que hacía absolutamente todo: leer la consola (`Scanner`), imprimir resultados (`System.out`), realizar las validaciones matemáticas (división por cero, logaritmos) y orquestar el flujo de la aplicación mediante un gran bloque `while` y condicionales `if/else`. 
**El problema:** Era un *God Object*. Imposible de testear de forma aislada, frágil ante los cambios y altamente acoplado a la infraestructura de la consola.

---

## Fase 1: Principio de Responsabilidad Única (SRP) 

### 💬 Interacción / Prompt
**Usuario:** "Fase 1: Principio de Responsabilidad Única (SRP) y DDD. Necesitamos separar la interfaz de usuario de la lógica de la Calculadora. Aplicando DDD, extrae las reglas de negocio de los números (ej. no permitir división por cero) en un Value Object llamado Operando. Dame primero los Test Unitarios."

### 📖 Explicación
El principio SRP dicta que "una clase debe tener una, y solo una, razón para cambiar". Esto significa que cada pieza de código debe encargarse de una única responsabilidad conceptual.

### 🛠️ ¿Cómo se diferenció del código anterior?
- **Antes:** La validación matemática estaba regada por toda la calculadora.
- **Después:** Creamos el Value Object `Operando`. Ahora, la responsabilidad de saber si un número es válido para una raíz o una división vive única y exclusivamente dentro de `Operando`. Además, se extrajo el motor matemático a una clase aislada `CalculadoraCore`, desentendiéndose por completo de la consola.

---

## Fase 2: Principio Abierto/Cerrado (OCP) 

###  Interacción / Prompt
 **Usuario:** "Fase 2: Principio Abierto/Cerrado (OCP). Modifica la CalculadoraCore para que no use un switch gigante. Crea una abstracción Operacion. Quiero poder agregar la operación 'Potencia' sin tocar la clase CalculadoraCore."

###  Explicación
El principio OCP establece que las entidades de software "deben estar abiertas para la extensión, pero cerradas para la modificación". Es decir, debemos poder agregar nuevas funcionalidades sin tener que alterar el código que ya funciona.

###  ¿Cómo se diferenció del código anterior?
- **Antes:** Cada vez que queríamos agregar una operación matemática, debíamos entrar a `CalculadoraCore` a agregar un nuevo `case` en un switch.
- **Después:** Creamos la interfaz `Operacion`. Ahora `CalculadoraCore` simplemente recibe una abstracción. Al añadir `Potencia`, solo creamos una nueva clase `Potencia implements Operacion` y el core la procesa de forma transparente sin que nadie toque su código fuente.

---

## Fase 3: Principio de Sustitución de Liskov (LSP) 

###  Interacción / Prompt
 **Usuario:** "Fase 3: Principio de Sustitución de Liskov (LSP). Tenemos un problema: las operaciones unarias (raíz) no necesitan dos operandos. Si una clase OperacionUnaria hereda de una interfaz que exige el método ejecutar(Operando a, Operando b) y lanza una excepción, violamos Liskov. Rediseña la jerarquía para que sea 100% sustituible."

### Explicación
El principio LSP exige que una clase derivada debe poder sustituir a su clase base sin romper el comportamiento esperado por el programa. Lanzar una excepción de "parámetro no soportado" al usar una clase hija viola este contrato implícito.

###  ¿Cómo se diferenció del código anterior?
- **Antes:** Si pasábamos 2 parámetros a la `RaizCuadrada`, esta explotaba, rompiendo la promesa genérica de la interfaz `Operacion`.
- **Después:** Aplicamos el *Patrón Command*. En lugar de inyectar los operandos en el método `ejecutar(a, b)`, se los inyectamos a cada operación a través de su constructor al momento de instanciarlas. Ahora, la firma universal es simplemente `ejecutar()`, garantizando que cualquier iterador pueda procesar listas mixtas sin miedo a excepciones de aridad.

---

## Fase 4: Principio de Segregación de Interfaces (ISP) 

###  Interacción / Prompt
> **Usuario:** "Fase 4: Segregación de Interfaces (ISP). Revisa nuestras abstracciones actuales. ¿Nuestra interfaz obliga a las clases a implementar métodos que no usan? Separa las interfaces si es necesario (interfaces componibles)."

### Explicación
El principio ISP advierte que "ningún cliente debe verse forzado a depender de métodos que no utiliza". Es preferible tener varias interfaces pequeñas y específicas que una sola interfaz "gorda" y monolítica.

### ¿Cómo se diferenció del código anterior?
- **Antes:** Si hubiéramos mantenido una interfaz con `setOperandoA` y `setOperandoB`, una operación unaria como la raíz se vería forzada a implementar `setOperandoB` dejando el método vacío o lanzando un error.
- **Después:** Segregamos las interfaces. Creamos `Operacion` (para ejecutar), `OperacionBinaria` (para inyectar 2 operandos) y `OperacionUnaria` (para inyectar 1). Ahora, clases como `Suma` se "componen" implementando múltiples interfaces precisas, manteniendo el código estrictamente atado a lo que realmente usa.

---

## Fase 5: Principio de Inversión de Dependencias (DIP) 

###  Interacción / Prompt
**Usuario:** "Fase 5: Inversión de Dependencias (DIP). Nuestra aplicación final no debe depender de System.out ni de Scanner directamente. Crea interfaces EntradaDatos y SalidaDatos. Inyecta estas dependencias en el orquestador."

### Explicación
El principio DIP tiene dos reglas: los módulos de alto nivel no deben depender de los módulos de bajo nivel; ambos deben depender de abstracciones. Y las abstracciones no deben depender de los detalles; los detalles deben depender de las abstracciones.

### ¿Cómo se diferenció del código anterior?
- **Antes:** Nuestro orquestador estaba fuertemente acoplado (hardcodeado) a `System.in` y `Scanner`. Probar este flujo sin una persona física escribiendo en el teclado era imposible.
- **Después:** Creamos las abstracciones `EntradaDatos` y `SalidaDatos`. Esto nos permitió crear Fakes/Mocks en nuestros tests unitarios para simular usuarios tecleando. En producción, la clase `Main` se encarga de instanciar las versiones reales (`ConsolaEntrada`, `ConsolaSalida`) y "conectarlas" (Inyección de Dependencias) en el constructor del `Orquestador`.
