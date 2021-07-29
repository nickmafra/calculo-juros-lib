package com.nickmafra.fin;

import java.math.BigDecimal;

public class CapitalizacaoExemploCalcularParcela {

    public static void main(String[] args) {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaPercentual(new BigDecimal("4.03"));
        juros.setTipoPeriodo(TipoPeriodo.MES);
        CapitalizacaoMultipla capitalizacaoMultipla = new CapitalizacaoMultipla(juros);

        capitalizacaoMultipla.addParcelasFixas(0, 24, BigDecimal.ZERO);
        capitalizacaoMultipla.setValorPresente(new BigDecimal("19000.00"));
        BigDecimal parcelaFixa = capitalizacaoMultipla.descubraValorParcelaFixa();

        System.out.println("Parcela fixa: " + parcelaFixa);
        System.out.println("Valor presente aproximado: " + capitalizacaoMultipla.getValorPresente());
    }
}
