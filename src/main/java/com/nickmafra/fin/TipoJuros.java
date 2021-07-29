package com.nickmafra.fin;

public enum TipoJuros {
    SIMPLES(CalculoJurosSimples.INSTANCE),
    COMPOSTO(CalculoJurosCompostos.INSTANCE),
    ;

    private final CalculoJuros calculo;

    TipoJuros(CalculoJuros calculo) {
        this.calculo = calculo;
    }

    public CalculoJuros getCalculo() {
        return calculo;
    }
}
