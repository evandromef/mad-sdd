package com.mad.investimentos.operacao;

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
@Table(name = "operacoes")
public class Operacao {

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
    private TipoOperacao tipo;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal quantidade;

    @Column(name = "valor_total", nullable = false, precision = 20, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "preco_unitario", precision = 20, scale = 8)
    private BigDecimal precoUnitario;

    @Column(precision = 20, scale = 2)
    private BigDecimal taxas;

    private String comentario;

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

    public TipoOperacao getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getTaxas() {
        return taxas;
    }

    public String getComentario() {
        return comentario;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }
}
