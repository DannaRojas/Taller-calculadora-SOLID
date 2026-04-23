# Respuestas sobre el diseño de la Calculadora SOLID

Aunque el requerimiento inicial pedía tener "todo en la misma clase" el diseño implementado en CalculadoraSolid.java mitiga este "desastre" utilizando clases estáticas anidadas e interfaces. Esto nos permite mantener todo en un solo archivo físico, pero lógicamente separado, respetando los principios SOLID.



### 01. ¿Qué responsabilidades tiene exactamente esta clase?
Físicamente, el archivo `CalculadoraSolid.java` agrupa todo. Sin embargo, lógicamente está dividido:
- **`OperacionBinaria` / `OperacionUnaria`**: Definen contratos (Abstracción).
- **`Suma`, `Division`, `RaizCuadrada`, etc.**: Tienen la única responsabilidad de ejecutar su cálculo matemático específico (Single Responsibility Principle).
- **`Calculadora` (Core)**: Tiene la responsabilidad de registrar las operaciones disponibles y delegar el cálculo a la operación correcta. No sabe *cómo* sumar o dividir, solo sabe a quién llamar.
- **`main`**: Tiene la responsabilidad exclusiva de manejar la interfaz de usuario (entrada/salida por consola) y ensamblar (inyectar dependencias) las operaciones en el core de la calculadora.

### 02. Si cambio la forma de imprimir en consola, ¿qué pasa con las operaciones?
**Absolutamente nada.** 
La lógica matemática (`Suma`, `Resta`, etc.) y el core (`Calculadora`) son completamente independientes de la consola. Devuelven un valor `double`. Si mañana decides cambiar la consola por una interfaz gráfica (JavaFX) o un endpoint REST (Spring Boot), las clases de operaciones y el core de la calculadora se pueden reutilizar sin tocar una sola línea de su código.

### 03. Si quiero tener 2 menús distintos, ¿qué debería modificar?
Solo tendrías que modificar o extraer el código que está dentro del método `main`. 
Podrías crear clases nuevas (ej. `MenuBasico` y `MenuAvanzado`) que instancien la misma clase `Calculadora` pero que ofrezcan diferentes opciones de texto al usuario. El motor de la calculadora (`Calculadora`) permanecería intacto.

### 04. ¿Cómo adiciono operaciones nuevas (ej. Potencia)? ¿Rompe algo más?
No rompe absolutamente nada (cumple con el **Open/Closed Principle**: abierto a extensión, cerrado a modificación). 
Para agregar la Potencia, solo debes seguir 2 pasos sin modificar el código existente:
1. Crear la nueva clase:
```java
public static class Potencia implements OperacionBinaria {
    @Override
    public double ejecutar(int a, int b) {
        return Math.pow(a, b);
    }
}
```
2. Registrarla en el `main` o en tu configurador:
```java
calc.registrarOperacionBinaria("^", new Potencia());
```
El menú y la lógica interna de `Calculadora` detectarán automáticamente la nueva operación.

### 05. ¿Qué pasa si ingreso validaciones de dominio (ej. no dividir por cero, no logaritmos negativos)? ¿Dónde irían en este desastre?
Las validaciones **ya están implementadas** en el código. Van exactamente dentro de la clase responsable de esa operación específica, no en el menú ni en el core. 

Por ejemplo, si revisas la clase `Division` en el código generado:
```java
public static class Division implements OperacionBinaria {
    @Override
    public double ejecutar(int a, int b) {
        if (b == 0) throw new ArithmeticException("No se puede dividir por cero");
        return (double) a / b;
    }
}
```
Y en `LogaritmoNatural`:
```java
public static class LogaritmoNatural implements OperacionUnaria {
    @Override
    public double ejecutar(int a) {
        if (a <= 0) throw new ArithmeticException("El logaritmo natural solo está definido para números mayores a cero");
        return Math.log(a);
    }
}
```
Al hacerlo de esta forma, delegamos la regla de negocio a quien le pertenece, evitando que el menú `main` o la clase `Calculadora` se llenen de sentencias `if/else` gigantes (evitando el antipatrón).
