package com.nickmafra.fin.exemplos;

import com.nickmafra.fin.CapitalizacaoMultipla;
import com.nickmafra.fin.Juros;
import com.nickmafra.fin.TipoJuros;
import com.nickmafra.fin.TipoPeriodo;

import java.math.BigDecimal;

public class ExemploCapitalizacaoCalcularValorPresente {

    public static void main(String[] args) {
        Juros juros = new Juros();
        juros.setTipoJuros(TipoJuros.COMPOSTO);
        juros.setTaxaPercentual(new BigDecimal("2.006393"));
        juros.setTipoPeriodo(TipoPeriodo.MES);
        CapitalizacaoMultipla capitalizacaoMultipla = new CapitalizacaoMultipla(juros);

        capitalizacaoMultipla.addParcelasFixas(0, 24, new BigDecimal("1000.00"));

        BigDecimal valorPresente = capitalizacaoMultipla.calculeValorPresente();
        System.out.println("Valor Presente: " + valorPresente);
    }
}
