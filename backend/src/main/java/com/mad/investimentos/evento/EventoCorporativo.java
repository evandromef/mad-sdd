package com.mad.investimentos.evento;

import com.mad.investimentos.ativo.Ativo;
import com.mad.investimentos.carteira.Carteira;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "eventos_corporativos")
public class EventoCorporativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carteira_id", nullable = false)
    private Carteira carteira;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ativo_id", nullable = false)
    private Ativo ativo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEventoCorporativo tipo;

    @Column(nullable = false)
    private LocalDate data;

    private String proporcao;

    @Column(name = "nova_quantidade", precision = 20, scale = 8)
    private BigDecimal novaQuantidade;

    @Column(name = "quantidade_recebida", precision = 20, scale = 8)
    private BigDecimal quantidadeRecebida;

    private String descricao;

    @Column(name = "data_criacao", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    public Long getId() {
        return id;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public TipoEventoCorporativo getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getProporcao() {
        return proporcao;
    }

    public BigDecimal getNovaQuantidade() {
        return novaQuantidade;
    }

    public BigDecimal getQuantidadeRecebida() {
        return quantidadeRecebida;
    }

    public String getDescricao() {
        return descricao;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }
}
