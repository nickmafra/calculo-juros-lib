package com.nickmafra.fin;

import java.math.BigDecimal;

public interface CalculoJuros {

    BigDecimal calculeValorJuros(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos);

    BigDecimal calculeValorFuturo(BigDecimal valorPresente, BigDecimal taxaReal, int qtPeriodos);

}
