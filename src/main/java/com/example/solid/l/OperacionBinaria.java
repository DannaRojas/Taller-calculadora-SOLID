package com.example.solid.l;

import com.example.solid.s.Operando;

public interface OperacionBinaria extends Operacion {
    Operando ejecutar(Operando a, Operando b);
}
