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

    @Test
    void descubraTaxaJuros_jurosCompostos() {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaReal(BigDecimal.ZERO);
        juros.setTipoPeriodo(TipoPeriodo.MES);
        CapitalizacaoMultipla capitalizacaoMultipla = new CapitalizacaoMultipla(juros);

        BigDecimal valorPresente = new BigDecimal("19000.00");
        capitalizacaoMultipla.setValorPresente(valorPresente);
        capitalizacaoMultipla.addParcelasFixas(0, 24, new BigDecimal("1375.20"));

        capitalizacaoMultipla.setArredondamentoIntermediario(new Arredondamento(10));
        capitalizacaoMultipla.descubraTaxaJuros(BigDecimal.ONE, new Arredondamento(10));
        BigDecimal valorPresenteAproximado = capitalizacaoMultipla.calculeValorPresente();

        assertEquals(valorPresente, valorPresenteAproximado);

        // a precisão atual do cálculo de taxa percentual é de 5 casas decimais
        BigDecimal taxaJurosArredondadaFinal = new Arredondamento(5)
                .arredondar(capitalizacaoMultipla.getJuros().getTaxaPercentual());

        assertEquals(new BigDecimal("4.98696"), taxaJurosArredondadaFinal);
    }
}
