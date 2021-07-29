package com.nickmafra.fin;

import java.math.BigDecimal;
import java.util.Objects;

public class Capitalizacao {

    private final Juros juros;
    private Arredondamento arredondamento = Arredondamento.PADRAO;
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

    public Arredondamento getArredondamento() {
        return arredondamento;
    }

    public void setArredondamento(Arredondamento arredondamento) {
        this.arredondamento = arredondamento;
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

        BigDecimal valorFinal = juros.getTipoJuros().getCalculo().calculeValorFuturo(valorInicial, juros.getTaxaReal(), qtPeriodos);
        return arredondamento.arredondar(valorFinal);
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
