package com.nickmafra.fin;

import java.math.BigDecimal;

public class CapitalizacaoExemploCalcularValorPresente {

    public static void main(String[] args) {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaPercentual(new BigDecimal("2.0064"));
        juros.setTipoPeriodo(TipoPeriodo.MES);
        CapitalizacaoMultipla capitalizacaoMultipla = new CapitalizacaoMultipla(juros);

        capitalizacaoMultipla.addParcelasFixas(0, 24, new BigDecimal("1000.00"));

        BigDecimal valorPresente = capitalizacaoMultipla.calculeValorPresente();
        System.out.println("Valor Presente: " + valorPresente);
    }
}
