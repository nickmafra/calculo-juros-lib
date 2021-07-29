package com.nickmafra.fin;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.nickmafra.fin.CalculoJurosSimples.INSTANCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculoJurosSimplesTest {

    Arredondamento arredondamento = Arredondamento.PADRAO;

    @Test
    void calculeValorJuros_1parcela() {
        BigDecimal valorJuros = INSTANCE.calculeValorJuros(new BigDecimal("1000.00"), new BigDecimal("0.01"), 1, arredondamento);
        valorJuros = valorJuros.setScale(2, RoundingMode.UNNECESSARY);
        assertEquals(new BigDecimal("10.00"), valorJuros);
    }

    @Test
    void calculeValorFuturo_1parcela() {
        BigDecimal valorFuturo = INSTANCE.calculeValorFuturo(new BigDecimal("1000.00"), new BigDecimal("0.01"), 1, arredondamento);
        valorFuturo = valorFuturo.setScale(2, RoundingMode.UNNECESSARY);
        assertEquals(new BigDecimal("1010.00"), valorFuturo);
    }

    @Test
    void calculeValorJuros_variasParcelas() {
        BigDecimal valorJuros = INSTANCE.calculeValorJuros(new BigDecimal("1000.00"), new BigDecimal("0.02"), 7, arredondamento);
        valorJuros = valorJuros.setScale(2, RoundingMode.UNNECESSARY);
        assertEquals(new BigDecimal("140.00"), valorJuros);
    }

    @Test
    void calculeValorFuturo_variasParcelas() {
        BigDecimal valorFuturo = INSTANCE.calculeValorFuturo(new BigDecimal("1000.00"), new BigDecimal("0.02"), 7, arredondamento);
        valorFuturo = valorFuturo.setScale(2, RoundingMode.UNNECESSARY);
        assertEquals(new BigDecimal("1140.00"), valorFuturo);
    }

    @Test
    void calculeValorJuros_1parcelaNegativa() {
        BigDecimal valorJuros = INSTANCE.calculeValorJuros(new BigDecimal("1000.00"), new BigDecimal("0.01"), -1, arredondamento);
        valorJuros = valorJuros.setScale(2, RoundingMode.UNNECESSARY);
        assertEquals(new BigDecimal("-10.00"), valorJuros);
    }

    @Test
    void calculeValorFuturo_1parcelaNegativa() {
        BigDecimal valorFuturo = INSTANCE.calculeValorFuturo(new BigDecimal("1000.00"), new BigDecimal("0.01"), -1, arredondamento);
        valorFuturo = valorFuturo.setScale(2, RoundingMode.UNNECESSARY);
        assertEquals(new BigDecimal("990.00"), valorFuturo);
    }
}
