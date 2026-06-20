CREATE TABLE proventos (
    id BIGSERIAL PRIMARY KEY,
    carteira_id BIGINT NOT NULL REFERENCES carteiras(id),
    ativo_id BIGINT NOT NULL REFERENCES ativos(id),
    tipo VARCHAR(20) NOT NULL,
    data DATE NOT NULL,
    valor_total NUMERIC(20, 2) NOT NULL,
    valor_por_unidade NUMERIC(20, 8) NULL,
    comentario TEXT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT ck_proventos_tipo CHECK (tipo IN ('DIVIDENDO', 'JCP', 'RENDIMENTO', 'AMORTIZACAO')),
    CONSTRAINT ck_proventos_valor_total CHECK (valor_total >= 0)
);

CREATE INDEX idx_proventos_carteira_ativo_data ON proventos(carteira_id, ativo_id, data);

