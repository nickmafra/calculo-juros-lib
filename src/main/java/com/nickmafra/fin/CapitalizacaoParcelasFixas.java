package com.nickmafra.fin;

import lombok.*;

import java.math.BigDecimal;

/**
 * Classe que permite calcular valores de um financiamento de parcelas fixas, com juros compostos.
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class CapitalizacaoParcelasFixas {

    @NonNull
    private Juros juros = new Juros();
    @NonNull
    private Arredondamento arredondamentoFinal = Arredondamento.PADRAO;
    @NonNull
    private Arredondamento arredondamentoIntermediario = Arredondamento.INTERMEDIARIO_PADRAO;

    @NonNull
    private BigDecimal valorParcela = BigDecimal.ZERO;
    private int qtParcelas;
    @NonNull
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
