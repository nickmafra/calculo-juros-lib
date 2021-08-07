package com.nickmafra.fin;

import lombok.*;

import java.math.BigDecimal;

/**
 * Classe que permite calcular valores de um financiamento de parcelas fixas, com juros compostos.
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CapitalizacaoParcelasFixas {

    @NonNull
    @Builder.Default
    private Juros juros = new Juros();
    @NonNull
    @Builder.Default
    private Arredondamento arredondamentoFinal = Arredondamento.PADRAO;
    @NonNull
    @Builder.Default
    private Arredondamento arredondamentoIntermediario = Arredondamento.INTERMEDIARIO_PADRAO;

    @NonNull
    @Builder.Default
    private BigDecimal valorParcela = BigDecimal.ZERO;
    private int qtParcelas;
    @NonNull
    @Builder.Default
    private BigDecimal valorPresente = BigDecimal.ZERO;

    private static void validarJuros(Juros juros) {
        if (juros.getTipoJuros() != TipoJuros.COMPOSTO)
            throw new IllegalArgumentException("Juros deve ser composto.");
    }

    private void validarQtParcelas() {
        if (qtParcelas < 0)
            throw new IllegalArgumentException("qtParcelas deve ser positivo.");
    }

    public BigDecimal calcularValorPresente() {
        validarJuros(juros);
        validarQtParcelas();
        if (valorParcela.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("valorParcela deve ser positivo.");

        valorPresente = CalculoJurosCompostos.INSTANCE.calculeValorPresente(
                juros.getTaxaReal(),
                qtParcelas,
                valorParcela,
                arredondamentoIntermediario,
                arredondamentoFinal
        );
        return valorPresente;
    }

    public BigDecimal calcularValorParcela() {
        validarJuros(juros);
        validarQtParcelas();
        if (valorPresente.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("valorPresente deve ser positivo.");

        valorParcela = CalculoJurosCompostos.INSTANCE.calculeValorParcela(
                juros.getTaxaReal(),
                qtParcelas,
                valorPresente,
                arredondamentoIntermediario,
                arredondamentoFinal
        );
        return valorParcela;
    }
}
