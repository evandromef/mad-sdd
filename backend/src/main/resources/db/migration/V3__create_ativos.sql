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

