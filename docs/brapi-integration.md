# Brapi Integration

## Base

- Base URL: `https://brapi.dev/api`
- Token: `BRAPI_TOKEN` por variavel de ambiente.
- Nunca hardcodar token.

## Endpoints

- Cotacao individual: `GET /quote/{ticker}?token={BRAPI_TOKEN}`
- Cotacao em lote: `GET /quote/{ticker1},{ticker2}?token={BRAPI_TOKEN}`

## Campos Relevantes

- `regularMarketPrice`: preco atual.
- `shortName`: nome resumido.
- `longName`: nome completo quando disponivel.
- `sector`: setor.
- `type`: tipo informado pela API quando disponivel.

## Tipo do Ativo

- Preferir `type` da Brapi quando confiavel.
- Fallback: tickers terminados em `11` podem ser tratados como FII se o resultado for compativel.
- A classificacao final deve ser revisavel pelo usuario antes de confirmar cadastro.

## Timeout e Falhas

- Connect timeout: 3s.
- Read timeout: 5s.
- Cadastro de ativo: se API falhar, bloquear com `ERR-004`.
- Atualizacao de cotacao: se API falhar, manter ultima cotacao e marcar desatualizada.

## Cache Backend

- Usar Caffeine no MVP.
- TTL: 60 minutos.
- Maximo: 500 entradas.
- `BrapiClient.buscarCotacao(ticker)` deve usar `@Cacheable(value = "cotacoes", key = "#ticker")`.
- Scheduler diario deve limpar cache antes de buscar novas cotacoes.

## Snapshot Mensal

- Ao atualizar cotacao, atualizar `cotacoes_atuais`.
- Para `cotacoes_mensais`, se ja existir `(ativo_id, ano_mes)`, sobrescrever preco; senao inserir.

