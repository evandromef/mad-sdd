# Integração com API da Bolsa Brasil — Brapi

> **Status:** Proposto  
> **Versão:** 1.2
> **Data:** 10/07/2026
> **Responsável:** Evandro Moreira  
> **Depende de:** [ADR-004](../arquitetura/adr/adr-004-provedor-cotacoes.md)

## 1. Objetivo

Definir o contrato técnico para manter o catálogo de ativos da bolsa brasileira e atualizar diariamente as cotações de Ações e FIIs usadas pelo MAD.

## 2. Provedor

- **Nome:** Brapi
- **Base:** `https://brapi.dev`
- **Formato:** REST/JSON sobre HTTPS
- **Documentação oficial:** [brapi.dev/docs](https://brapi.dev/docs)
- **Cobertura usada:** Ações e FIIs negociados no mercado brasileiro.

O plano gratuito da Brapi limita a estratégia de consulta de cotações do MAD a um ativo por chamada. O código não deve agrupar múltiplos tickers na mesma requisição de cotação.

## 3. Autenticação e segredos

Em produção, toda chamada autenticada envia:

```http
Authorization: Bearer <BRAPI_TOKEN>
```

- O token é lido da variável `BRAPI_TOKEN` exclusivamente pelo backend.
- É proibido enviar o token por query string, frontend, logs, métricas ou mensagens de erro.
- A aplicação deve falhar na inicialização do componente de integração quando a credencial obrigatória estiver ausente no ambiente que habilita o job.
- Logs registram apenas endpoint lógico, status, duração, ticker consultado e identificador de correlação.

## 4. Endpoints

| Finalidade | Método e endpoint | Uso |
| --- | --- | --- |
| Cotação por ativo | `GET /api/v2/stocks/quote?symbols={ticker}` | Job diário e validação da última cotação |
| Catálogo de ativos | Endpoint de listagem de tickers/ativos disponível na API v2 | Carga inicial e atualização interna de ativos |

O endpoint de catálogo deverá ser confirmado contra o contrato Brapi vigente durante a implementação. Mudança de versão, endpoint ou provedor exige atualização deste documento e avaliação do ADR-004.

## 5. Catálogo de ativos

### 5.1 Finalidade

O catálogo de ativos é a base mestre usada pelo MAD para disponibilizar Ações e FIIs ao usuário. O usuário não cadastra ativos manualmente; ele apenas seleciona ativos existentes nesse catálogo.

### 5.2 Carga inicial

- Executar chamada ao endpoint de catálogo da Brapi.
- Filtrar apenas ativos classificados como Ação ou FII dentro do escopo do MVP.
- Normalizar tickers em maiúsculas antes da persistência.
- Cadastrar ativos inexistentes com, no mínimo, ticker, nome, categoria, status cadastral e provedor.
- Rejeitar itens sem ticker, sem nome suficiente para exibição ou sem categoria compatível com o escopo.
- Registrar falhas por item sem abortar toda a carga quando houver itens válidos.

### 5.3 Atualização periódica

- Atualizar o catálogo de forma periódica, em agenda própria e independente do job diário de cotações.
- Incluir novos ativos retornados pela API.
- Atualizar nome, categoria e status cadastral de ativos já existentes quando houver mudança válida.
- Não excluir fisicamente ativos ausentes da resposta do provedor.
- Ativo ausente, suspenso, encerrado ou sem negociação deve ser marcado como indisponível para novas seleções, preservando vínculos históricos.
- Ativos já referenciados em carteiras continuam consultáveis nos históricos mesmo quando indisponíveis para novos lançamentos.

### 5.4 Mapeamento do catálogo

| Campo Brapi | Campo MAD | Regra |
| --- | --- | --- |
| Ticker/código de negociação | `ativo.ticker` | normalizar em maiúsculas; único |
| Nome curto ou nome longo | `ativo.nome` | preferir nome mais adequado à exibição |
| Tipo/categoria | `ativo.categoria` | aceitar apenas `ACAO` ou `FII` |
| Status de negociação, quando disponível | `ativo.status_negociacao` | mapear para status interno configurado |
| Provedor | `ativo.provedor` | valor fixo `BRAPI` |
| Instante da sincronização | `ativo.ultima_sincronizacao_at` | horário do processamento no backend |

O contrato exato dos campos Brapi deve ser validado durante a implementação contra a documentação vigente. Campos adicionais podem ser armazenados somente se houver uso funcional ou operacional claro.

## 6. Cotação por ativo

### 6.1 Requisição

```http
GET /api/v2/stocks/quote?symbols=PETR4 HTTP/1.1
Host: brapi.dev
Authorization: Bearer <BRAPI_TOKEN>
Accept: application/json
```

Os tickers são deduplicados e validados antes do processamento. Cada requisição deve conter exatamente um ticker em `symbols`, em razão da limitação do plano gratuito da API.

### 6.2 Estrutura relevante da resposta

```json
{
  "results": [
    {
      "requestedSymbol": "PETR4",
      "symbol": "PETR4",
      "changed": false,
      "data": {
        "shortName": "PETROBRAS PN",
        "longName": "Petróleo Brasileiro S.A. - Petrobras",
        "currency": "BRL",
        "regularMarketPrice": 38.50,
        "regularMarketTime": "2026-06-14T17:08:00.000Z"
      }
    }
  ],
  "requestedAt": "2026-06-14T17:08:02.000Z",
  "took": 245
}
```

O exemplo representa apenas os campos consumidos e não congela campos adicionais do provedor.

### 6.3 Mapeamento

| Campo Brapi | Campo MAD | Regra |
| --- | --- | --- |
| `results[].symbol` | `ativo.ticker` | normalizar em maiúsculas |
| `results[].data.shortName` | `ativo.nome` | usar quando disponível |
| `results[].data.currency` | `cotacao_atual.moeda` | aceitar `BRL` para o escopo atual |
| `results[].data.regularMarketPrice` | `cotacao_atual.preco` | desserializar diretamente em `BigDecimal` |
| `results[].data.regularMarketTime` | `cotacao_atual.data_referencia` | converter para `Instant` sem perder fuso |
| `requestedAt` | `cotacao_atual.obtida_at` | instante da resposta; usar relógio do servidor apenas se ausente |

É proibido desserializar preço por `double` ou `float`. Resposta sem símbolo, preço positivo, moeda aceita ou data válida não substitui a cotação anterior.

## 7. Agendamento e processamento

### 7.1 Job de catálogo

- Execução em agenda configurável, independente do job diário de cotações.
- Deve ser executável manualmente em ambiente administrativo ou operacional durante implantação.
- Usa trava distribuída no PostgreSQL para permitir somente uma execução entre réplicas.
- A carga inicial e as atualizações periódicas devem ser idempotentes por ticker.
- Falha total mantém o catálogo anterior.
- Falha parcial persiste itens válidos e registra os itens rejeitados.

### 7.2 Job diário de cotações

- Execução em todos os dias úteis da semana (SEG, TER, QUA, QUI, SEX) às 21h em `America/Sao_Paulo`.
- Consulta somente ativos únicos referenciados em carteiras de usuários.
- Não consulta ativos presentes apenas no catálogo.
- Executa uma chamada individual para cada ticker.
- Usa trava distribuída no PostgreSQL para permitir somente uma execução entre réplicas.
- Cada cotação válida é persistida transacionalmente, sem exigir sucesso das demais consultas.
- Reexecução no mesmo dia é idempotente: uma cotação mais antiga não substitui outra mais recente.

## 8. Timeout, retry e rate limit

- Timeout total por requisição: 10 segundos.
- Máximo: três tentativas.
- Backoff exponencial com jitter.
- Retry somente para timeout, falha de conexão, HTTP `429` e erros `5xx` transitórios.
- Respeitar `Retry-After` quando fornecido.
- Não repetir automaticamente erros `4xx` permanentes, exceto `429`.
- A consulta de cotação é unitária por ativo. Paralelismo, se habilitado, deve respeitar o rate limit do plano Brapi.
- Ao receber `401` ou `403`, interromper o job e registrar falha de configuração sem revelar a credencial.

## 9. Tratamento de falhas

| Situação | Comportamento |
| --- | --- |
| Falha total da Brapi | Manter todas as últimas cotações válidas. |
| Falha parcial | Persistir itens válidos e manter a cotação anterior dos demais. |
| Símbolo não retornado | Marcar falha individual; não remover o ativo. |
| Payload inválido | Rejeitar o item e preservar valor anterior. |
| Cotação antiga | Persistir somente se for mais recente que a armazenada. |
| Catálogo indisponível | Manter catálogo anterior e registrar falha operacional. |
| Ativo ausente no catálogo atualizado | Marcar como indisponível para novas seleções, sem exclusão física automática. |
| Rate limit | Respeitar `Retry-After` e encerrar de forma controlada após o limite de tentativas. |

A API do MAD sempre retorna preço junto de `dataReferencia` e indicação de desatualização calculada por regra configurável. Nunca retorna zero como substituto de cotação ausente.

## 10. Cliente Spring

- O `BrapiClient` fica no módulo de integração com dados financeiros e é acessado por portas de catálogo de ativos e de cotações.
- DTOs externos não atravessam a fronteira do módulo; são mapeados para tipos internos.
- URL base, token, timeout, tentativas, horários e habilitação dos jobs são propriedades externas.
- Cache local é permitido somente para catálogo/metadados de ativos e nunca contém o token.
- Testes usam servidor HTTP simulado; testes automatizados não dependem da Brapi real.

Variáveis previstas:

| Variável | Conteúdo |
| --- | --- |
| `BRAPI_BASE_URL` | URL base do provedor |
| `BRAPI_TOKEN` | segredo de autenticação |
| `BRAPI_TIMEOUT` | timeout da chamada |
| `BRAPI_MAX_ATTEMPTS` | tentativas máximas |
| `BRAPI_CATALOG_JOB_CRON` | expressão do job de catálogo |
| `BRAPI_QUOTES_JOB_CRON` | expressão do job diário de cotações |
| `BRAPI_JOB_ZONE` | `America/Sao_Paulo` |

Nenhuma variável recebe segredo ou URL privada como valor padrão versionado.

## 11. Testes de contrato e integração

- carga inicial do catálogo;
- atualização periódica com inclusão de novo ativo;
- atualização periódica com ativo indisponível;
- rejeição de item de catálogo sem ticker ou categoria compatível;
- resposta válida para consulta unitária de ticker;
- Ação e FII;
- preço com precisão decimal;
- símbolo alterado ou ausente;
- item inválido em resposta parcialmente válida;
- timeout, `429`, `401`, `403` e `5xx`;
- `Retry-After`, backoff e limite de tentativas;
- preservação da última cotação válida;
- filtro de cotação diária apenas para ativos referenciados em carteiras;
- garantia de que a rotina não envia múltiplos tickers na mesma requisição de cotação;
- idempotência do job diário de cotações;
- concorrência entre duas instâncias do job;
- segredo ausente e garantia de não exposição em logs.

## 12. Decisões pendentes

- Plano Brapi contratado e seus limites efetivos.
- Endpoint exato de catálogo de ativos na Brapi e campos retornados.
- Periodicidade final do job de catálogo.
- Regra temporal exata para o indicador visual de cotação desatualizada, caso uma ESPEC defina limite adicional ao uso da última cotação válida.
- Estratégia futura para histórico de cotações, fora do MVP enquanto o plano gratuito da Brapi não contemplar esse recurso.
- Observabilidade e alertas operacionais, adiados para após o desenvolvimento.

## 13. Referências

- [Documentação oficial Brapi](https://brapi.dev/docs)
- [ADR-004](../arquitetura/adr/adr-004-provedor-cotacoes.md)
- [ERS](../requisitos/ers.md), especialmente RF-034, RN-017 e RN-018
- [Modelo de Dados](../arquitetura/modelo_dados.md)
- [ESPEC-09 - Catálogo de Ativos](../requisitos/especs/ESPEC_09_catalogo-de-ativos.md)
