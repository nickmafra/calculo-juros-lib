package com.nickmafra.fin;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public class BuscaBinaria {

    private static final int QT_MAXIMA_TENTATIVAS = 100;

    private BuscaBinaria() {
    }

    public static BigDecimal descubra(BigDecimal respostaAlvo, BigDecimal tentativaMaxima, UnaryOperator<BigDecimal> funcaoTentativa, Arredondamento arredondamentoFinal, Arredondamento arredondamentoIntermediario) {
        BigDecimal respostaAproximada;
        BigDecimal diferencaResposta;
        BigDecimal tentativa = tentativaMaxima;
        BigDecimal diferencaTentativa = tentativaMaxima;
        int direcao = 0;
        int qtTentativas = 0;
        do {
            if (qtTentativas >= QT_MAXIMA_TENTATIVAS)
                return tentativa; // já tentou demais

            BigDecimal respostaAproximadaNova = funcaoTentativa.apply(tentativa);
            qtTentativas++;
            diferencaResposta = respostaAlvo.subtract(respostaAproximadaNova);
            respostaAproximada = respostaAproximadaNova;

            if (direcao == 0) {
                // a tentativa inicial é para descobrir a direção tentativa-resposta, se é direta ou inversa.
                direcao = -diferencaResposta.signum();
            }

            BigDecimal diferencaTentativaNova = arredondamentoIntermediario.dividirArredondando(diferencaTentativa, Calculo.DOIS);
            if (diferencaTentativaNova.compareTo(diferencaTentativa) == 0) {
                return tentativa; // a aproximação chegou ao limite
            }
            diferencaTentativa = diferencaTentativaNova;

            if (diferencaResposta.signum() * direcao > 0) {
                tentativa = tentativa.add(diferencaTentativa);
            } else {
                tentativa = tentativa.subtract(diferencaTentativa);
            }
        } while (respostaAproximada.compareTo(respostaAlvo) != 0);

        return arredondamentoFinal.arredondar(tentativa);
    }
}
