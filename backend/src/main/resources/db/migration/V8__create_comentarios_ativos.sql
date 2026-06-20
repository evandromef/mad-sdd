CREATE TABLE comentarios_ativos (
    id BIGSERIAL PRIMARY KEY,
    carteira_id BIGINT NOT NULL REFERENCES carteiras(id),
    ativo_id BIGINT NOT NULL REFERENCES ativos(id),
    texto TEXT NOT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    data_atualizacao TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_comentarios_carteira_ativo ON comentarios_ativos(carteira_id, ativo_id);

