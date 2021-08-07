package com.nickmafra.fin;

import lombok.*;

import java.math.BigDecimal;

import static com.nickmafra.fin.Calculo.percentualParaReal;
import static com.nickmafra.fin.Calculo.realParaPercentual;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class Juros {

    @NonNull
    private TipoPeriodo tipoPeriodo = TipoPeriodo.MES;
    @NonNull
    private TipoJuros tipoJuros = TipoJuros.COMPOSTO;
    /**
     * A taxa de juros em valor real (não percentual).
     * Uma taxa real de 0,05 é equivalente a uma taxa percentual de 5%.
     */
    @NonNull
    private BigDecimal taxaReal = BigDecimal.ZERO;

    public BigDecimal getTaxaPercentual() {
        return realParaPercentual(taxaReal);
    }

    public void setTaxaPercentual(@NonNull BigDecimal taxaPercentual) {
        this.taxaReal = percentualParaReal(taxaPercentual);
    }
}
