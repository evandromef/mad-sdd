package com.mad.investimentos.posicao;

import com.mad.investimentos.evento.EventoCorporativo;
import com.mad.investimentos.evento.EventoCorporativoRepository;
import com.mad.investimentos.evento.TipoEventoCorporativo;
import com.mad.investimentos.operacao.Operacao;
import com.mad.investimentos.operacao.OperacaoRepository;
import com.mad.investimentos.operacao.TipoOperacao;
import com.mad.investimentos.shared.BusinessException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PosicoesService {

    private static final BigDecimal ZERO_BRL = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    private static final int INTERNAL_SCALE = 12;

    private final OperacaoRepository operacaoRepository;
    private final EventoCorporativoRepository eventoCorporativoRepository;

    public PosicoesService(
            OperacaoRepository operacaoRepository,
            EventoCorporativoRepository eventoCorporativoRepository) {
        this.operacaoRepository = operacaoRepository;
        this.eventoCorporativoRepository = eventoCorporativoRepository;
    }

    @Transactional(readOnly = true)
    public PosicaoDTO calcularPosicao(Long carteiraId, Long ativoId) {
        List<MovimentoPosicao> movimentos = new ArrayList<>();
        operacaoRepository.findByCarteiraIdAndAtivoId(carteiraId, ativoId)
                .stream()
                .map(this::toMovimento)
                .forEach(movimentos::add);
        eventoCorporativoRepository.findByCarteiraIdAndAtivoId(carteiraId, ativoId)
                .stream()
                .map(this::toMovimento)
                .forEach(movimentos::add);

        ResultadoPosicao resultado = calcular(movimentos);
        return new PosicaoDTO(
                carteiraId,
                ativoId,
                normalizarQuantidade(resultado.quantidadeAtual()),
                normalizarMoeda(resultado.custoTotal()),
                ZERO_BRL,
                ZERO_BRL,
                BigDecimal.ZERO);
    }

    public ResultadoPosicao calcular(List<MovimentoPosicao> movimentos) {
        return calcular(movimentos, false);
    }

    public ResultadoPosicao validarHistoricoRetroativo(List<MovimentoPosicao> movimentos) {
        return calcular(movimentos, true);
    }

    private ResultadoPosicao calcular(List<MovimentoPosicao> movimentos, boolean alteracaoRetroativa) {
        BigDecimal quantidadeAtual = BigDecimal.ZERO;
        BigDecimal custoTotal = BigDecimal.ZERO;

        for (MovimentoPosicao movimento : ordenar(movimentos)) {
            if (movimento.tipoMovimento() == TipoMovimentoPosicao.EVENTO) {
                if (movimento.tipoEvento() == TipoEventoCorporativo.BONIFICACAO) {
                    quantidadeAtual = quantidadeAtual.add(requerPositivo(movimento.quantidadeRecebida()));
                } else {
                    quantidadeAtual = requerPositivo(movimento.novaQuantidade());
                }
            } else if (movimento.tipoOperacao() == TipoOperacao.COMPRA) {
                quantidadeAtual = quantidadeAtual.add(requerPositivo(movimento.quantidade()));
                custoTotal = custoTotal.add(requerNaoNegativo(movimento.valorTotal()));
            } else {
                BigDecimal quantidadeVendida = requerPositivo(movimento.quantidade());
                if (quantidadeAtual.compareTo(quantidadeVendida) < 0) {
                    throw saldoInsuficiente(alteracaoRetroativa);
                }

                BigDecimal custoBaixado = custoTotal
                        .divide(quantidadeAtual, INTERNAL_SCALE, RoundingMode.HALF_UP)
                        .multiply(quantidadeVendida);
                quantidadeAtual = quantidadeAtual.subtract(quantidadeVendida);
                custoTotal = custoTotal.subtract(custoBaixado);
            }

            if (quantidadeAtual.signum() < 0) {
                throw saldoInsuficiente(alteracaoRetroativa);
            }
            if (quantidadeAtual.signum() == 0) {
                custoTotal = BigDecimal.ZERO;
            }
        }

        return new ResultadoPosicao(normalizarQuantidade(quantidadeAtual), normalizarMoeda(custoTotal));
    }

    private MovimentoPosicao toMovimento(Operacao operacao) {
        if (operacao.getTipo() == TipoOperacao.COMPRA) {
            return MovimentoPosicao.compra(
                    operacao.getId(),
                    operacao.getData(),
                    operacao.getDataCriacao(),
                    operacao.getQuantidade(),
                    operacao.getValorTotal());
        }
        return MovimentoPosicao.venda(
                operacao.getId(),
                operacao.getData(),
                operacao.getDataCriacao(),
                operacao.getQuantidade(),
                operacao.getValorTotal());
    }

    private MovimentoPosicao toMovimento(EventoCorporativo evento) {
        if (evento.getTipo() == TipoEventoCorporativo.BONIFICACAO) {
            return MovimentoPosicao.bonificacao(
                    evento.getId(),
                    evento.getData(),
                    evento.getDataCriacao(),
                    evento.getQuantidadeRecebida());
        }
        return MovimentoPosicao.splitOuGrupamento(
                evento.getId(),
                evento.getData(),
                evento.getDataCriacao(),
                evento.getTipo(),
                evento.getNovaQuantidade());
    }

    private List<MovimentoPosicao> ordenar(List<MovimentoPosicao> movimentos) {
        return movimentos.stream()
                .sorted(Comparator
                        .comparing(MovimentoPosicao::data)
                        .thenComparingInt(this::prioridade)
                        .thenComparing(m -> m.dataCriacao() == null ? OffsetDateTime.MIN : m.dataCriacao())
                        .thenComparing(m -> m.id() == null ? 0L : m.id()))
                .toList();
    }

    private int prioridade(MovimentoPosicao movimento) {
        return movimento.tipoMovimento() == TipoMovimentoPosicao.EVENTO ? 0 : 1;
    }

    private BigDecimal requerPositivo(BigDecimal valor) {
        if (valor == null || valor.signum() <= 0) {
            throw new BusinessException(
                    "ERR-007",
                    "Valor informado deve ser maior que zero.",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return valor;
    }

    private BigDecimal requerNaoNegativo(BigDecimal valor) {
        if (valor == null || valor.signum() < 0) {
            throw new BusinessException(
                    "ERR-007",
                    "Valor informado deve ser maior que zero.",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return valor;
    }

    private BusinessException saldoInsuficiente(boolean alteracaoRetroativa) {
        if (!alteracaoRetroativa) {
            return new BusinessException(
                    "ERR-006",
                    "Quantidade de venda maior que a posicao atual.",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new BusinessException(
                "ERR-013",
                "Alteracao rejeitada porque deixaria o historico com quantidade negativa.",
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private BigDecimal normalizarQuantidade(BigDecimal valor) {
        return valor.stripTrailingZeros();
    }

    private BigDecimal normalizarMoeda(BigDecimal valor) {
        if (valor.signum() == 0) {
            return ZERO_BRL;
        }
        return valor.setScale(2, RoundingMode.HALF_UP);
    }
}
