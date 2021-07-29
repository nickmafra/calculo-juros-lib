package com.nickmafra.fin;

import java.math.BigDecimal;

public class CapitalizacaoExemploCalcularValorPresente2 {

    public static void main(String[] args) {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaPercentual(new BigDecimal("4.03"));
        juros.setTipoPeriodo(TipoPeriodo.MES);
        CapitalizacaoMultipla capitalizacaoMultipla = new CapitalizacaoMultipla(juros);

        capitalizacaoMultipla.addParcelasFixas(0, 24, new BigDecimal("1375.20"));

        BigDecimal valorPresente = capitalizacaoMultipla.calculeValorPresente();
        System.out.println("Valor Presente: " + valorPresente);
    }
}
