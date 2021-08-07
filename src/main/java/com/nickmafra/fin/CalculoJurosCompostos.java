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

    private BigDecimal calculeFatorParcelaFixa(BigDecimal taxaReal, int qtPeriodos, Arredondamento arredondamento) {
        // [ 1 - (1 + j) ^ -n ] / j
        BigDecimal numerador = Calculo.integerPow(taxaReal.add(BigDecimal.ONE), -qtPeriodos, arredondamento)
                .negate().add(BigDecimal.ONE);
        return arredondamento.dividirArredondando(numerador, taxaReal);
    }

    public BigDecimal calculeValorPresente(BigDecimal taxaReal, int qtPeriodos, BigDecimal valorParcela, Arredondamento arredondamentoIntermediario, Arredondamento arredondamentoFinal) {
        BigDecimal fator = calculeFatorParcelaFixa(taxaReal, qtPeriodos, arredondamentoIntermediario);
        return arredondamentoFinal.arredondar(fator.multiply(valorParcela));
    }

    public BigDecimal calculeValorParcela(BigDecimal taxaReal, int qtPeriodos, BigDecimal valorPresente, Arredondamento arredondamentoIntermediario, Arredondamento arredondamentoFinal) {
        BigDecimal fator = calculeFatorParcelaFixa(taxaReal, qtPeriodos, arredondamentoIntermediario);
        return arredondamentoFinal.dividirArredondando(valorPresente, fator);
    }
}
