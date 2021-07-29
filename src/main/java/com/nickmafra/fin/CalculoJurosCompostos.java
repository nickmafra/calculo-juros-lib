package com.nickmafra.fin;

import java.math.BigDecimal;

public class CalculoJurosCompostos implements CalculoJuros {

    public static final CalculoJurosCompostos INSTANCE = new CalculoJurosCompostos();

    private CalculoJurosCompostos() {
    }

    @Override
    public BigDecimal calculeValorFuturo(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos, Arredondamento arredondamento) {
        BigDecimal taxaTotal = Calculo.integerPow(taxaReal.add(BigDecimal.ONE), qtPeriodos, arredondamento);
        return valorPresente.multiply(taxaTotal);
    }

    @Override
    public BigDecimal calculeValorJuros(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos, Arredondamento arredondamento) {
        return calculeValorFuturo(valorPresente, taxaReal, qtPeriodos, arredondamento).subtract(valorPresente);
    }
}
