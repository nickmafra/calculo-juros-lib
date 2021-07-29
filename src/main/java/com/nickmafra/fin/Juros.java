package com.nickmafra.fin;

import java.math.BigDecimal;
import java.util.Objects;

import static com.nickmafra.fin.Calculo.percentualParaReal;
import static com.nickmafra.fin.Calculo.realParaPercentual;

public class Juros {

    private TipoPeriodo tipoPeriodo;
    private TipoJuros tipoJuros;
    private BigDecimal taxaReal;

    public void validate() {
        Objects.requireNonNull(tipoPeriodo);
        Objects.requireNonNull(tipoJuros);
        Objects.requireNonNull(taxaReal);
    }

    public TipoPeriodo getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(TipoPeriodo tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public TipoJuros getTipoJuros() {
        return tipoJuros;
    }

    public void setTipoJuros(TipoJuros tipoJuros) {
        this.tipoJuros = tipoJuros;
    }

    public BigDecimal getTaxaReal() {
        return taxaReal;
    }

    public void setTaxaReal(BigDecimal taxaReal) {
        this.taxaReal = taxaReal;
    }

    public BigDecimal getTaxaPercentual() {
        return realParaPercentual(taxaReal);
    }

    public void setTaxaPercentual(BigDecimal taxaPercentual) {
        this.taxaReal = percentualParaReal(taxaPercentual);
    }
}
