package com.nickmafra.fin;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalizacaoParcelasFixasTest {

    @Test
    void calcularValorParcela() {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaPercentual(new BigDecimal("4.03"));
        juros.setTipoPeriodo(TipoPeriodo.MES);

        CapitalizacaoParcelasFixas capitalizacao = new CapitalizacaoParcelasFixas();
        capitalizacao.setJuros(juros);

        capitalizacao.setQtParcelas(24);
        capitalizacao.setValorPresente(new BigDecimal("19000.00"));
        BigDecimal parcelaFixa = capitalizacao.calcularValorParcela();

        assertEquals(new BigDecimal("1249.98"), parcelaFixa);
    }

    @Test
    void calcularValorPresente() {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaPercentual(new BigDecimal("4.03"));
        juros.setTipoPeriodo(TipoPeriodo.MES);

        CapitalizacaoParcelasFixas capitalizacao = new CapitalizacaoParcelasFixas();
        capitalizacao.setJuros(juros);

        capitalizacao.setQtParcelas(24);
        capitalizacao.setValorParcela(new BigDecimal("1249.98"));
        BigDecimal valorPresente = capitalizacao.calcularValorPresente();

        assertEquals(new BigDecimal("19000.00"), valorPresente);
    }

    @Test
    void calcularValorParcelaCopiado() {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaPercentual(new BigDecimal("9.03"));
        juros.setTipoPeriodo(TipoPeriodo.MES);

        CapitalizacaoParcelasFixas capitalizacao = new CapitalizacaoParcelasFixas();
        capitalizacao.setJuros(juros);

        capitalizacao.setQtParcelas(24);
        capitalizacao.setValorPresente(new BigDecimal("19000.00"));

        Juros novoJuros = juros.toBuilder()
                .taxaReal(Calculo.percentualParaReal(new BigDecimal("4.03")))
                .build();
        CapitalizacaoParcelasFixas novaCapitalizacao = capitalizacao.toBuilder()
                .juros(novoJuros)
                .build();
        BigDecimal parcelaFixa = novaCapitalizacao.calcularValorParcela();

        assertEquals(new BigDecimal("1249.98"), parcelaFixa);
    }
}