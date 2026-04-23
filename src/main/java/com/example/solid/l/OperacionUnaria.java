package com.example.solid.l;

import com.example.solid.s.Operando;

public interface OperacionUnaria extends Operacion {
    Operando ejecutar(Operando a);
}
