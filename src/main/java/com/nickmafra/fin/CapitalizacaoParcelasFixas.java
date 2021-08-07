package com.nickmafra.fin;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe que permite calcular valores de um financiamento de parcelas fixas, com juros compostos.
 */
public class CapitalizacaoParcelasFixas {

    private Juros juros = new Juros();
    private Arredondamento arredondamentoFinal = Arredondamento.PADRAO;
    private Arredondamento arredondamentoIntermediario = Arredondamento.INTERMEDIARIO_PADRAO;

    private BigDecimal valorParcela = null;
    private Integer qtParcelas = null;
    private BigDecimal valorPresente = null;

    public Juros getJuros() {
        return juros;
    }

    public void setJuros(Juros juros) {
        Objects.requireNonNull(juros);
        validarJuros(juros);
        this.juros = juros;
    }

    public Arredondamento getArredondamentoFinal() {
        return arredondamentoFinal;
    }

    public void setArredondamentoFinal(Arredondamento arredondamentoFinal) {
        Objects.requireNonNull(arredondamentoFinal);
        this.arredondamentoFinal = arredondamentoFinal;
    }

    public Arredondamento getArredondamentoIntermediario() {
        return arredondamentoIntermediario;
    }

    public void setArredondamentoIntermediario(Arredondamento arredondamentoIntermediario) {
        Objects.requireNonNull(arredondamentoIntermediario);
        this.arredondamentoIntermediario = arredondamentoIntermediario;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public int getQtParcelas() {
        return qtParcelas;
    }

    public void setQtParcelas(int qtParcelas) {
        this.qtParcelas = qtParcelas;
    }

    public BigDecimal getValorPresente() {
        return valorPresente;
    }

    public void setValorPresente(BigDecimal valorPresente) {
        this.valorPresente = valorPresente;
    }

    private static void validarJuros(Juros juros) {
        juros.validate();
        if (juros.getTipoJuros() != TipoJuros.COMPOSTO)
            throw new IllegalArgumentException("Juros deve ser composto.");
    }

    private void validarQtParcelas() {
        Objects.requireNonNull(qtParcelas, "qtParcelas deve ser preenchido.");
        if (qtParcelas < 0)
            throw new IllegalArgumentException("qtParcelas deve ser positivo.");
    }

    public BigDecimal calcularValorPresente() {
        validarJuros(juros);
        validarQtParcelas();
        Objects.requireNonNull(valorParcela, "valorParcela deve ser preenchido.");
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
        Objects.requireNonNull(valorPresente, "valorPresente deve ser preenchido.");
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
