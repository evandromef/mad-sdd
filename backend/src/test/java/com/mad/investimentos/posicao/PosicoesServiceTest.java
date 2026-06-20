package com.mad.investimentos.posicao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.mad.investimentos.evento.TipoEventoCorporativo;
import com.mad.investimentos.shared.BusinessException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class PosicoesServiceTest {

    private final PosicoesService service = new PosicoesService(null, null);
    private final LocalDate hoje = LocalDate.of(2026, 6, 18);
    private final OffsetDateTime agora = OffsetDateTime.parse("2026-06-18T10:00:00-03:00");

    @Test
    void calculaCustoTotalComComprasVendaSplitEBonificacao() {
        ResultadoPosicao resultado = service.calcular(List.of(
                MovimentoPosicao.compra(1L, hoje, agora, bd("10"), bd("2500.00")),
                MovimentoPosicao.compra(2L, hoje.plusDays(1), agora.plusDays(1), bd("5"), bd("1200.00")),
                MovimentoPosicao.venda(3L, hoje.plusDays(2), agora.plusDays(2), bd("5"), bd("1500.00")),
                MovimentoPosicao.splitOuGrupamento(4L, hoje.plusDays(3), agora.plusDays(3),
                        TipoEventoCorporativo.SPLIT, bd("20")),
                MovimentoPosicao.bonificacao(5L, hoje.plusDays(4), agora.plusDays(4), bd("3"))));

        assertThat(resultado.quantidadeAtual()).isEqualByComparingTo("23");
        assertThat(resultado.custoTotal()).isEqualByComparingTo("2466.67");
    }

    @Test
    void vendaQueZeraPosicaoForcaCustoTotalZero() {
        ResultadoPosicao resultado = service.calcular(List.of(
                MovimentoPosicao.compra(1L, hoje, agora, bd("10"), bd("2500.00")),
                MovimentoPosicao.venda(2L, hoje.plusDays(1), agora.plusDays(1), bd("10"), bd("2600.00"))));

        assertThat(resultado.quantidadeAtual()).isEqualByComparingTo("0");
        assertThat(resultado.custoTotal()).isEqualByComparingTo("0.00");
    }

    @Test
    void rejeitaVendaMaiorQueSaldoComErroCatalogado() {
        assertThatThrownBy(() -> service.calcular(List.of(
                MovimentoPosicao.compra(1L, hoje, agora, bd("10"), bd("2500.00")),
                MovimentoPosicao.venda(2L, hoje.plusDays(1), agora.plusDays(1), bd("20"), bd("5000.00")))))
                .isInstanceOf(BusinessException.class)
                .extracting("code")
                .isEqualTo("ERR-006");
    }

    @Test
    void validacaoRetroativaMapeiaSaldoNegativoParaErroDeHistorico() {
        assertThatThrownBy(() -> service.validarHistoricoRetroativo(List.of(
                MovimentoPosicao.compra(1L, hoje, agora, bd("10"), bd("2500.00")),
                MovimentoPosicao.venda(2L, hoje.plusDays(1), agora.plusDays(1), bd("20"), bd("5000.00")))))
                .isInstanceOf(BusinessException.class)
                .extracting("code")
                .isEqualTo("ERR-013");
    }

    @Test
    void aplicaEventoAntesDeOperacaoNoMesmoDia() {
        ResultadoPosicao resultado = service.calcular(List.of(
                MovimentoPosicao.compra(1L, hoje.minusDays(1), agora.minusDays(1), bd("10"), bd("2500.00")),
                MovimentoPosicao.venda(3L, hoje, agora.minusHours(1), bd("5"), bd("1000.00")),
                MovimentoPosicao.splitOuGrupamento(2L, hoje, agora, TipoEventoCorporativo.SPLIT, bd("20"))));

        assertThat(resultado.quantidadeAtual()).isEqualByComparingTo("15");
        assertThat(resultado.custoTotal()).isEqualByComparingTo("1875.00");
    }

    private BigDecimal bd(String valor) {
        return new BigDecimal(valor);
    }
}
