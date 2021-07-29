package com.nickmafra.fin;

import java.math.BigDecimal;

public class Calculo {

    public static final BigDecimal DOIS = BigDecimal.valueOf(2);
    public static final BigDecimal CEM = BigDecimal.valueOf(100);

    private Calculo() {
    }

    public static BigDecimal realParaPercentual(BigDecimal valorReal) {
        return valorReal.movePointRight(2);
    }

    public static BigDecimal percentualParaReal(BigDecimal valorPercentual) {
        return valorPercentual.movePointLeft(2);
    }

    public static BigDecimal integerPow(BigDecimal valor, int expoente, Arredondamento arredondamento) {
        BigDecimal potenciaPositiva = valor.pow(Math.abs(expoente));
        if (expoente >= 0)
            return potenciaPositiva;
        else
            return arredondamento.dividirArredondando(BigDecimal.ONE, potenciaPositiva);
    }
}
