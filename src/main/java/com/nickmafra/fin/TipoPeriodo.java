package com.nickmafra.fin;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public enum TipoPeriodo {
    DIA(ChronoUnit.DAYS),
    MES(ChronoUnit.MONTHS),
    ANO(ChronoUnit.YEARS),
    ;

    private final TemporalUnit unidade;

    TipoPeriodo(TemporalUnit unidade) {
        this.unidade = unidade;
    }

    public TemporalUnit getUnidade() {
        return unidade;
    }
}
