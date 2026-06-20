package com.mad.investimentos.posicao;

import java.math.BigDecimal;

public record PosicaoDTO(
        Long carteiraId,
        Long ativoId,
        BigDecimal quantidadeAtual,
        BigDecimal custoTotal,
        BigDecimal valorMercado,
        BigDecimal pnlNaoRealizado,
        BigDecimal percentualAlocacao
) {
}
