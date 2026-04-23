# Charlemos sobre el diseño de la Calculadora SOLID ☕

¡Hola! Sé que al principio sonaba un poco a locura pedir que metiéramos "todo en la misma clase" (eso suele terminar en un monstruo de mil cabezas que los programadores llamamos *God Object*). Pero no te preocupes, para evitar ese desastre, usamos un pequeño truco: **interfaces y clases estáticas anidadas**. 

Básicamente, logramos cumplir tu regla de usar un solo archivo (`CalculadoraSolid.java`), pero internamente todo está súper organizadito y separado. ¡Aquí te explico cómo funciona!

### 01. ¿Qué responsabilidades tiene exactamente esta clase? 🤔
Si miras el archivo físico, sí, ahí está todo amontonado. Pero lógicamente cada pieza sabe cuál es su trabajo:
- **Las reglas del juego (`OperacionBinaria` / `OperacionUnaria`)**: Solo dicen "oye, las operaciones deben funcionar así".
- **Los trabajadores (`Suma`, `Division`, `RaizCuadrada`, etc.)**: Cada uno hace solo lo suyo. La suma solo suma y no le importa nada más en el mundo.
- **El jefe (`Calculadora`)**: Su único trabajo es tener la lista de operaciones disponibles y pasarle el trabajo al trabajador correcto. No tiene ni idea de matemáticas.
- **El presentador (`main`)**: Es la cara visible. Habla con el usuario, toma los datos por consola y le pasa todo al jefe para que haga la magia. 

### 02. Si cambio la forma de imprimir en consola, ¿qué pasa con las operaciones? 🖥️
**¡Absolutamente nada!** 😎
La matemática y la lógica de la calculadora no saben qué es una consola. Solo devuelven números. Si mañana te aburres de la consolita negra y quieres hacer una página web súper moderna o una app móvil, puedes llevarte las clases de operaciones tal cual están, sin cambiarles ni una coma.

### 03. Si quiero tener 2 menús distintos, ¿qué debería modificar? 📝
Solo tendrías que tocar la parte del presentador (el método `main` o sacar esa lógica a otra parte). 
Podrías tener un "Menú para niños" y un "Menú científico", que le muestren textos distintos al usuario. Pero el motor de la calculadora ni se entera, seguirá funcionando igual de bien con ambos menús.

### 04. ¿Cómo adiciono operaciones nuevas (ej. Potencia)? ¿Rompe algo más? 🚀
¡Cero estrés! No vas a romper nada porque el código está pensado para crecer sin tener que tocar lo que ya funciona (a esto le llamamos principio Abierto/Cerrado). 

Para agregar la Potencia, solo haces dos cosas fáciles:
1. Creas al nuevo trabajador:
```java
public static class Potencia implements OperacionBinaria {
    @Override
    public double ejecutar(int a, int b) {
        return Math.pow(a, b);
    }
}
```
2. Y le avisas al jefe (`main`) que ya lo contrataste:
```java
calc.registrarOperacionBinaria("^", new Potencia());
```
¡Y listo! El menú y la calculadora lo reconocerán automáticamente.

### 05. ¿Qué pasa si ingreso validaciones de dominio (ej. no dividir por cero, no logaritmos negativos)? ¿Dónde irían en este desastre? 🛡️
¡Buena pregunta! De hecho, **ya me adelanté y las incluí** en el código desde el principio. 😉

En lugar de llenar el menú o al pobre jefe de reglas de `if/else`, le dimos esa responsabilidad a cada trabajador. Por ejemplo, el que divide es el único que sabe que no se puede dividir por cero.

Míralo en la clase `Division`:
```java
public static class Division implements OperacionBinaria {
    @Override
    public double ejecutar(int a, int b) {
        if (b == 0) throw new ArithmeticException("¡Ouch! No se puede dividir por cero");
        return (double) a / b;
    }
}
```
Y lo mismo para el `LogaritmoNatural`:
```java
public static class LogaritmoNatural implements OperacionUnaria {
    @Override
    public double ejecutar(int a) {
        if (a <= 0) throw new ArithmeticException("Hmm, el logaritmo solo funciona con números mayores a cero");
        return Math.log(a);
    }
}
```
Así cada quien cuida su propio jardín y el código se mantiene limpio y libre de desastres. ✨
