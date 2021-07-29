package com.nickmafra.fin;

import java.math.BigDecimal;

public class CapitalizacaoExemploCalcularTaxa {

    public static void main(String[] args) {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaReal(BigDecimal.ZERO);
        juros.setTipoPeriodo(TipoPeriodo.MES);
        CapitalizacaoMultipla capitalizacaoMultipla = new CapitalizacaoMultipla(juros);

        capitalizacaoMultipla.setValorPresente(new BigDecimal("27977.00"));
        capitalizacaoMultipla.addParcelasFixas(0, 6, new BigDecimal("2000.00"));
        capitalizacaoMultipla.addParcelasFixas(6, 24, new BigDecimal("1000.00"));

        capitalizacaoMultipla.descubraTaxaJuros(BigDecimal.ONE, new Arredondamento(4));
        BigDecimal valorPresente = capitalizacaoMultipla.calculeValorPresente();

        System.out.println("Taxa percentual de juros: " + capitalizacaoMultipla.getJuros().getTaxaPercentual());
        System.out.println("Valor Presente aproximado: " + valorPresente);
    }
}
