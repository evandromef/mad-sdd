CREATE INDEX idx_ativos_ticker ON ativos(ticker);
CREATE INDEX idx_operacoes_data ON operacoes(data);
CREATE INDEX idx_eventos_data ON eventos_corporativos(data);
CREATE INDEX idx_proventos_data ON proventos(data);
CREATE INDEX idx_cotacoes_atuais_data ON cotacoes_atuais(data_atualizacao);

