CREATE TABLE cotacoes_atuais (
    ativo_id BIGINT PRIMARY KEY REFERENCES ativos(id),
    data_atualizacao TIMESTAMPTZ NOT NULL,
    preco NUMERIC(20, 8) NOT NULL,
    fonte VARCHAR(80) NOT NULL,
    falha_ultima_atualizacao BOOLEAN NOT NULL DEFAULT false,
    CONSTRAINT ck_cotacoes_atuais_preco CHECK (preco >= 0)
);

CREATE TABLE cotacoes_mensais (
    id BIGSERIAL PRIMARY KEY,
    ativo_id BIGINT NOT NULL REFERENCES ativos(id),
    ano_mes CHAR(7) NOT NULL,
    preco NUMERIC(20, 8) NOT NULL,
    fonte VARCHAR(80) NOT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_cotacoes_mensais_ativo_mes UNIQUE (ativo_id, ano_mes),
    CONSTRAINT ck_cotacoes_mensais_preco CHECK (preco >= 0)
);

CREATE INDEX idx_cotacoes_mensais_ativo_mes ON cotacoes_mensais(ativo_id, ano_mes);

