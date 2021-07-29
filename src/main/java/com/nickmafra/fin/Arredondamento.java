package com.nickmafra.fin;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Arredondamento {

    public static final Arredondamento PADRAO = new Arredondamento(2);
    public static final Arredondamento INTERMEDIARIO_PADRAO = new Arredondamento(6);

    private final Integer escala;
    private final RoundingMode modo;

    public Arredondamento(Integer escala, RoundingMode modo) {
        this.escala = escala;
        this.modo = modo;
    }

    public Arredondamento(Integer escala) {
        this(escala, RoundingMode.HALF_UP);
    }

    public BigDecimal arredondar(BigDecimal valor) {
        return valor.setScale(escala, modo);
    }

    public BigDecimal dividirArredondando(BigDecimal valor, BigDecimal divisor) {
        return valor.divide(divisor, escala, modo);
    }
}
