CREATE TABLE operacoes (
    id BIGSERIAL PRIMARY KEY,
    carteira_id BIGINT NOT NULL REFERENCES carteiras(id),
    ativo_id BIGINT NOT NULL REFERENCES ativos(id),
    tipo VARCHAR(10) NOT NULL,
    data DATE NOT NULL,
    quantidade NUMERIC(20, 8) NOT NULL,
    valor_total NUMERIC(20, 2) NOT NULL,
    preco_unitario NUMERIC(20, 8) NULL,
    taxas NUMERIC(20, 2) NULL,
    comentario TEXT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT ck_operacoes_tipo CHECK (tipo IN ('COMPRA', 'VENDA')),
    CONSTRAINT ck_operacoes_quantidade CHECK (quantidade > 0),
    CONSTRAINT ck_operacoes_valor_total CHECK (valor_total >= 0)
);

CREATE INDEX idx_operacoes_carteira_ativo_data ON operacoes(carteira_id, ativo_id, data, data_criacao, id);
