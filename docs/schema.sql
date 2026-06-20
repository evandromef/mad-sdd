-- MAD - schema conceitual para PostgreSQL
-- ADR-002: posicoes nao sao materializadas. O PosicoesService calcula sob demanda.

CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    nome VARCHAR(160) NOT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE carteiras (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
    nome VARCHAR(120) NOT NULL,
    descricao TEXT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_carteiras_usuario_nome UNIQUE (usuario_id, nome)
);

CREATE TABLE ativos (
    id BIGSERIAL PRIMARY KEY,
    ticker VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(180) NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    setor VARCHAR(120) NULL,
    segmento VARCHAR(120) NULL,
    ativo BOOLEAN NOT NULL DEFAULT true,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT ck_ativos_tipo CHECK (tipo IN ('ACAO', 'FII'))
);

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

CREATE TABLE comentarios_ativos (
    id BIGSERIAL PRIMARY KEY,
    carteira_id BIGINT NOT NULL REFERENCES carteiras(id),
    ativo_id BIGINT NOT NULL REFERENCES ativos(id),
    texto TEXT NOT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    data_atualizacao TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_carteiras_usuario ON carteiras(usuario_id);
CREATE INDEX idx_operacoes_carteira_ativo_data ON operacoes(carteira_id, ativo_id, data, data_criacao, id);
CREATE INDEX idx_eventos_carteira_ativo_data ON eventos_corporativos(carteira_id, ativo_id, data, data_criacao, id);
CREATE INDEX idx_proventos_carteira_ativo_data ON proventos(carteira_id, ativo_id, data);
CREATE INDEX idx_cotacoes_mensais_ativo_mes ON cotacoes_mensais(ativo_id, ano_mes);
CREATE INDEX idx_comentarios_carteira_ativo ON comentarios_ativos(carteira_id, ativo_id);
