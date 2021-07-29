package com.nickmafra.fin;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalizacaoMultiplaTest {

    @Test
    void descubraValorParcelaFixa_jurosCompostos() {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaPercentual(new BigDecimal("4.03"));
        juros.setTipoPeriodo(TipoPeriodo.MES);
        CapitalizacaoMultipla capitalizacaoMultipla = new CapitalizacaoMultipla(juros);

        capitalizacaoMultipla.addParcelasFixas(0, 24, BigDecimal.ZERO);
        capitalizacaoMultipla.setValorPresente(new BigDecimal("19000.00"));
        BigDecimal parcelaFixa = capitalizacaoMultipla.descubraValorParcelaFixa();

        assertEquals(new BigDecimal("1249.98"), parcelaFixa);
    }
}
