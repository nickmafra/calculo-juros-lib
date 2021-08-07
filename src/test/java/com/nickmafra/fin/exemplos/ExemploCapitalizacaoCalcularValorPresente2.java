package com.nickmafra.fin.exemplos;

import com.nickmafra.fin.CapitalizacaoMultipla;
import com.nickmafra.fin.Juros;
import com.nickmafra.fin.TipoJuros;
import com.nickmafra.fin.TipoPeriodo;

import java.math.BigDecimal;

public class ExemploCapitalizacaoCalcularValorPresente2 {

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
