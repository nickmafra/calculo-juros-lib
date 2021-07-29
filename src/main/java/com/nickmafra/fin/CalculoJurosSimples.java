package com.nickmafra.fin;

import java.math.BigDecimal;

public class CalculoJurosSimples implements CalculoJuros {

    public static final CalculoJurosSimples INSTANCE = new CalculoJurosSimples();

    private CalculoJurosSimples() {
    }

    @Override
    public BigDecimal calculeValorJuros(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos, Arredondamento arredondamento) {
        BigDecimal taxaTotal = taxaReal.multiply(BigDecimal.valueOf(qtPeriodos));
        return valorPresente.multiply(taxaTotal);
    }

    @Override
    public BigDecimal calculeValorFuturo(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos, Arredondamento arredondamento) {
        return calculeValorJuros(valorPresente, taxaReal, qtPeriodos, arredondamento).add(valorPresente);
    }
}
