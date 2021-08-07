package com.nickmafra.fin;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Capitalizacao {

    @NonNull
    private final Juros juros;
    @NonNull
    private Arredondamento arredondamentoFinal = Arredondamento.PADRAO;
    @NonNull
    private Arredondamento arredondamentoIntermediario = Arredondamento.INTERMEDIARIO_PADRAO;
    @NonNull
    private BigDecimal valorPresente = BigDecimal.ZERO;
    private int qtPeriodos;
    @NonNull
    private BigDecimal valorFuturo = BigDecimal.ZERO;

    private BigDecimal calculeValor(@NonNull BigDecimal valorInicial, int qtPeriodos) {
        BigDecimal valorFinal = juros.getTipoJuros().getCalculo()
                .calculeValorFuturo(valorInicial, juros.getTaxaReal(), qtPeriodos, arredondamentoIntermediario);
        return arredondamentoFinal.arredondar(valorFinal);
    }

    public BigDecimal calculeValorFuturo() {
        valorFuturo = calculeValor(valorPresente, qtPeriodos);
        return valorFuturo;
    }

    public BigDecimal calculeValorPresente() {
        valorPresente = calculeValor(valorFuturo, -qtPeriodos);
        return valorPresente;
    }
}
