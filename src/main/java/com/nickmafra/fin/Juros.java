package com.nickmafra.fin;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

import static com.nickmafra.fin.Calculo.percentualParaReal;
import static com.nickmafra.fin.Calculo.realParaPercentual;

@Data
public class Juros {

    @NonNull
    private TipoPeriodo tipoPeriodo = TipoPeriodo.MES;
    @NonNull
    private TipoJuros tipoJuros = TipoJuros.COMPOSTO;
    @NonNull
    private BigDecimal taxaReal = BigDecimal.ZERO;

    public BigDecimal getTaxaPercentual() {
        return realParaPercentual(taxaReal);
    }

    public void setTaxaPercentual(@NonNull BigDecimal taxaPercentual) {
        this.taxaReal = percentualParaReal(taxaPercentual);
    }
}
