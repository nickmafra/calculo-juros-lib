package com.nickmafra.fin;

import lombok.*;

import java.math.BigDecimal;

import static com.nickmafra.fin.Calculo.percentualParaReal;
import static com.nickmafra.fin.Calculo.realParaPercentual;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Juros {

    @NonNull
    @Builder.Default
    private TipoPeriodo tipoPeriodo = TipoPeriodo.MES;
    @NonNull
    @Builder.Default
    private TipoJuros tipoJuros = TipoJuros.COMPOSTO;
    /**
     * A taxa de juros em valor real (não percentual).
     * Uma taxa real de 0,05 é equivalente a uma taxa percentual de 5%.
     */
    @NonNull
    @Builder.Default
    private BigDecimal taxaReal = BigDecimal.ZERO;

    public BigDecimal getTaxaPercentual() {
        return realParaPercentual(taxaReal);
    }

    public void setTaxaPercentual(@NonNull BigDecimal taxaPercentual) {
        this.taxaReal = percentualParaReal(taxaPercentual);
    }
}
