package com.nickmafra.fin;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculoJurosCompostos implements CalculoJuros {

    public static final CalculoJurosCompostos INSTANCE = new CalculoJurosCompostos();

    private CalculoJurosCompostos() {
    }

    @Override
    public BigDecimal calculeValorFuturo(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos) {
        BigDecimal taxaTotal = taxaReal.add(BigDecimal.ONE).pow(Math.abs(qtPeriodos));
        if (qtPeriodos < 0)
            return valorPresente.divide(taxaTotal, RoundingMode.HALF_UP);
        else
            return valorPresente.multiply(taxaTotal);
    }

    @Override
    public BigDecimal calculeValorJuros(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos) {
        return calculeValorFuturo(valorPresente, taxaReal, qtPeriodos).subtract(valorPresente);
    }
}
