package com.nickmafra.fin;

import java.math.BigDecimal;
import java.util.Objects;

public class Capitalizacao {

    private final Juros juros;
    private Arredondamento arredondamentoFinal = Arredondamento.PADRAO;
    private Arredondamento arredondamentoIntermediario = Arredondamento.INTERMEDIARIO_PADRAO;
    private BigDecimal valorPresente = BigDecimal.ZERO;
    private int qtPeriodos;
    private BigDecimal valorFuturo = BigDecimal.ZERO;

    public Capitalizacao(Juros juros) {
        Objects.requireNonNull(juros);
        this.juros = juros;
    }

    public Juros getJuros() {
        return juros;
    }

    public Arredondamento getArredondamentoFinal() {
        return arredondamentoFinal;
    }

    public void setArredondamentoFinal(Arredondamento arredondamentoFinal) {
        this.arredondamentoFinal = arredondamentoFinal;
    }

    public Arredondamento getArredondamentoIntermediario() {
        return arredondamentoIntermediario;
    }

    public void setArredondamentoIntermediario(Arredondamento arredondamentoIntermediario) {
        this.arredondamentoIntermediario = arredondamentoIntermediario;
    }

    public BigDecimal getValorPresente() {
        return valorPresente;
    }

    public void setValorPresente(BigDecimal valorPresente) {
        Objects.requireNonNull(valorPresente);
        this.valorPresente = valorPresente;
    }

    public int getQtPeriodos() {
        return qtPeriodos;
    }

    public void setQtPeriodos(int qtPeriodos) {
        this.qtPeriodos = qtPeriodos;
    }

    public BigDecimal getValorFuturo() {
        return valorFuturo;
    }

    public void setValorFuturo(BigDecimal valorFuturo) {
        Objects.requireNonNull(valorFuturo);
        this.valorFuturo = valorFuturo;
    }

    private BigDecimal calculeValor(BigDecimal valorInicial, int qtPeriodos) {
        Objects.requireNonNull(valorInicial);
        juros.validate();

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
