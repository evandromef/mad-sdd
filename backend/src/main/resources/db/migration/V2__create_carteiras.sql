CREATE TABLE carteiras (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
    nome VARCHAR(120) NOT NULL,
    descricao TEXT NULL,
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_carteiras_usuario_nome UNIQUE (usuario_id, nome)
);

CREATE INDEX idx_carteiras_usuario ON carteiras(usuario_id);

