package com.nickmafra.fin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class CapitalizacaoMultipla {

    private final Juros juros;
    private Arredondamento arredondamento = Arredondamento.PADRAO;
    private final List<Capitalizacao> capitalizacoes = new ArrayList<>();
    private BigDecimal valorPresente = BigDecimal.ZERO;

    public CapitalizacaoMultipla(Juros juros) {
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

    public List<Capitalizacao> getCapitalizacoes() {
        return Collections.unmodifiableList(capitalizacoes);
    }

    public Capitalizacao addCapitalizacao(int qtPeriodos, BigDecimal valorFuturo) {
        Capitalizacao capitalizacao = new Capitalizacao(juros);
        capitalizacao.setArredondamento(arredondamento);
        capitalizacao.setQtPeriodos(qtPeriodos);
        capitalizacao.setValorFuturo(valorFuturo);
        capitalizacoes.add(capitalizacao);
        return capitalizacao;
    }

    public void removeCapitalizacao(Capitalizacao capitalizacao) {
        capitalizacoes.remove(capitalizacao);
    }

    public void addParcelasFixas(int periodoCarencia, int qtParcelas, BigDecimal valorParcela) {
        int qtPeriodos = periodoCarencia;
        for (int i = 0; i < qtParcelas; i++) {
            addCapitalizacao(++qtPeriodos, valorParcela);
        }
    }

    public void setParcelaFixa(BigDecimal valorParcela) {
        for (Capitalizacao capitalizacao : capitalizacoes) {
            capitalizacao.setValorFuturo(valorParcela);
        }
    }

    public BigDecimal getValorPresente() {
        return valorPresente;
    }

    public void setValorPresente(BigDecimal valorPresente) {
        this.valorPresente = valorPresente;
    }

    private BigDecimal calculeValor(Function<Capitalizacao, BigDecimal> getter) {
        BigDecimal valor = capitalizacoes.stream()
                .map(getter)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return arredondamento.arredondar(valor);
    }

    public BigDecimal calculeValorPresente() {
        valorPresente = calculeValor(Capitalizacao::calculeValorPresente);
        return valorPresente;
    }

    public BigDecimal descubraValorParcelaFixa() {
        return BuscaBinaria.descubra(valorPresente, valorPresente, arredondamento, v -> {
            setParcelaFixa(v);
            return calculeValorPresente();
        });
    }

    public BigDecimal descubraTaxaJuros(BigDecimal taxaRealMaxima, Arredondamento arredondamento) {
        return BuscaBinaria.descubra(valorPresente, taxaRealMaxima, arredondamento, t -> {
            juros.setTaxaReal(t);
            return calculeValorPresente();
        });
    }
}
