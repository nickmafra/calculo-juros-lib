package com.nickmafra.fin;

import java.math.BigDecimal;

public interface CalculoJuros {

    BigDecimal calculeValorJuros(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos, Arredondamento arredondamento);

    BigDecimal calculeValorFuturo(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos, Arredondamento arredondamento);

}
