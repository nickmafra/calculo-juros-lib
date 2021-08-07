package com.nickmafra.fin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoJuros {
    SIMPLES(CalculoJurosSimples.INSTANCE),
    COMPOSTO(CalculoJurosCompostos.INSTANCE),
    ;

    private final CalculoJuros calculo;
}
