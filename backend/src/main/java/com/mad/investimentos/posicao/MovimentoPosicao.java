package com.mad.investimentos.posicao;

import com.mad.investimentos.evento.TipoEventoCorporativo;
import com.mad.investimentos.operacao.TipoOperacao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record MovimentoPosicao(
        Long id,
        LocalDate data,
        OffsetDateTime dataCriacao,
        TipoMovimentoPosicao tipoMovimento,
        TipoOperacao tipoOperacao,
        TipoEventoCorporativo tipoEvento,
        BigDecimal quantidade,
        BigDecimal valorTotal,
        BigDecimal novaQuantidade,
        BigDecimal quantidadeRecebida
) {

    public static MovimentoPosicao compra(Long id, LocalDate data, OffsetDateTime dataCriacao,
            BigDecimal quantidade, BigDecimal valorTotal) {
        return new MovimentoPosicao(
                id,
                data,
                dataCriacao,
                TipoMovimentoPosicao.OPERACAO,
                TipoOperacao.COMPRA,
                null,
                quantidade,
                valorTotal,
                null,
                null);
    }

    public static MovimentoPosicao venda(Long id, LocalDate data, OffsetDateTime dataCriacao,
            BigDecimal quantidade, BigDecimal valorTotal) {
        return new MovimentoPosicao(
                id,
                data,
                dataCriacao,
                TipoMovimentoPosicao.OPERACAO,
                TipoOperacao.VENDA,
                null,
                quantidade,
                valorTotal,
                null,
                null);
    }

    public static MovimentoPosicao splitOuGrupamento(Long id, LocalDate data, OffsetDateTime dataCriacao,
            TipoEventoCorporativo tipoEvento, BigDecimal novaQuantidade) {
        return new MovimentoPosicao(
                id,
                data,
                dataCriacao,
                TipoMovimentoPosicao.EVENTO,
                null,
                tipoEvento,
                null,
                null,
                novaQuantidade,
                null);
    }

    public static MovimentoPosicao bonificacao(Long id, LocalDate data, OffsetDateTime dataCriacao,
            BigDecimal quantidadeRecebida) {
        return new MovimentoPosicao(
                id,
                data,
                dataCriacao,
                TipoMovimentoPosicao.EVENTO,
                null,
                TipoEventoCorporativo.BONIFICACAO,
                null,
                null,
                null,
                quantidadeRecebida);
    }
}
