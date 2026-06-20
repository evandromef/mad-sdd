package com.mad.investimentos.posicao;

import java.math.BigDecimal;

public record ResultadoPosicao(
        BigDecimal quantidadeAtual,
        BigDecimal custoTotal
) {
}
