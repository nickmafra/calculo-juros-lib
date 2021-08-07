package com.nickmafra.fin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Getter
@RequiredArgsConstructor
public enum TipoPeriodo {
    DIA(ChronoUnit.DAYS),
    MES(ChronoUnit.MONTHS),
    ANO(ChronoUnit.YEARS),
    ;

    private final TemporalUnit unidade;
}
