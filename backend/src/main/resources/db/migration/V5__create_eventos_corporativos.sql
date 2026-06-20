CREATE TABLE eventos_corporativos (
    id BIGSERIAL PRIMARY KEY,
    carteira_id BIGINT NOT NULL REFERENCES carteiras(id),
    ativo_id BIGINT NOT NULL REFERENCES ativos(id),
    tipo VARCHAR(20) NOT NULL,
    data DATE NOT NULL,
    proporcao VARCHAR(30) NULL,
    nova_quantidade NUMERIC(20, 8) NULL,
    quantidade_recebida NUMERIC(20, 8) NULL,
    descricao TEXT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT ck_eventos_tipo CHECK (tipo IN ('SPLIT', 'GRUPAMENTO', 'BONIFICACAO')),
    CONSTRAINT ck_eventos_split_grupamento CHECK (
        (tipo IN ('SPLIT', 'GRUPAMENTO') AND nova_quantidade IS NOT NULL AND quantidade_recebida IS NULL)
        OR (tipo = 'BONIFICACAO' AND quantidade_recebida IS NOT NULL AND nova_quantidade IS NULL)
    )
);

CREATE INDEX idx_eventos_carteira_ativo_data ON eventos_corporativos(carteira_id, ativo_id, data, data_criacao, id);
