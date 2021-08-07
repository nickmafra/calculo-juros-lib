package com.nickmafra.fin.exemplos;

import com.nickmafra.fin.Calculo;
import com.nickmafra.fin.CapitalizacaoParcelasFixas;
import com.nickmafra.fin.Juros;
import lombok.Value;
import lombok.val;

import java.math.BigDecimal;
import java.util.ArrayList;

@Value
public class ExemploComparativoParcelasFixas {

    public static void main(String[] args) {
        val bancos = new ArrayList<Banco>();
        bancos.add(new Banco("XBank", new BigDecimal("6.03")));
        bancos.add(new Banco("YBank", new BigDecimal("4.03")));
        bancos.add(new Banco("ZBank", new BigDecimal("3.03")));

        val capBasica = new CapitalizacaoParcelasFixas();
        capBasica.setValorPresente(new BigDecimal("15000.00"));
        capBasica.setQtParcelas(24);

        bancos.forEach(banco -> {
            val capBanco = capBasica.toBuilder().juros(banco.juros).build();
            capBanco.calcularValorParcela();
            System.out.println("Financiamento para banco " + banco.nome + ": " + toTextoExibicao(capBanco));
        });
    }

    static class Banco {
        String nome;
        Juros juros;

        Banco(String nome, BigDecimal taxaPercentual) {
            this.nome = nome;
            this.juros = Juros.builder()
                    .taxaReal(Calculo.percentualParaReal(taxaPercentual))
                    .build();
        }
    }

    static String toTextoExibicao(CapitalizacaoParcelasFixas cap) {
        return cap.getValorPresente() + ", pago em " + cap.getQtParcelas() + " parcelas de " + cap.getValorParcela() + " cada.";
    }
}
