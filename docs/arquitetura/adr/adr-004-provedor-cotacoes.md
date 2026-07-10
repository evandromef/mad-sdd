# ADR-004: Provedor de Dados da Bolsa de Valores do Brasil

- **Data:** 07/07/2026
- **Responsável:** Evandro Moreira

## Contexto

O MAD precisa manter um catálogo de Ações e FIIs do mercado brasileiro e atualizar diariamente as cotações dos ativos referenciados em carteiras de usuários, mantendo a última cotação válida quando o provedor falhar.

## Decisão

- Usar a Brapi por sua cobertura do mercado brasileiro e disponibilidade de dados de ativos e cotações.
- Manter o catálogo interno de ativos a partir da API externa, sem cadastro manual de ativos pelo usuário.
- Consumir `GET /api/v2/stocks/quote?symbols={ticker}` via HTTPS, com um ativo por requisição.
- Confirmar e documentar o endpoint de catálogo/listagem de ativos da Brapi durante a implementação.
- Autenticar no header `Authorization: Bearer`, com token vindo de `BRAPI_TOKEN`.
- Executar atualização diária (SEG, TER, QUA, QUI, SEX) de cotações às 21h em `America/Sao_Paulo`, consultando um ativo por vez por limitação do plano gratuito.
- Buscar cotações somente para ativos referenciados em carteiras de usuários.
- Aplicar timeout de 10 segundos e até três tentativas com backoff exponencial e jitter.
- Persistir sucessos parciais e preservar a última cotação válida dos ativos que falharem.
- Não armazenar histórico de cotações nem snapshot mensal no MVP, pois o plano gratuito da Brapi não contempla histórico de cotações.

## Alternativas consideradas

| Alternativa | Motivo da rejeição |
| --- | --- |
| B3 diretamente | Integração mais complexa para o escopo do MVP. |
| Yahoo Finance | Menor especialização e contrato menos alinhado ao mercado brasileiro. |
| Cadastro manual de ativos pelo usuário | Aumenta risco de inconsistência de ticker, categoria e duplicidade cadastral. |
| Atualização em tempo real | Fora do escopo e desnecessária para os requisitos. |

## Consequências

- O sistema depende dos limites e da disponibilidade do plano Brapi.
- A indisponibilidade da Brapi pode atrasar a atualização do catálogo, mas não remove ativos já cadastrados.
- Paralelismo, se habilitado, deverá respeitar o rate limit do plano gratuito; não haverá agrupamento de múltiplos tickers em uma mesma chamada.
- O usuário sempre verá a data/hora da cotação utilizada.
- Ativos presentes apenas no catálogo não consomem chamadas da rotina diária de cotações.
- Evolução histórica do valor de mercado da carteira ou do ativo fica fora do MVP até haver provedor/plano com histórico de cotações.
- O cliente nunca recebe o token Brapi.

## Referências

- ERS: RF-034, RF-041, RF-042, RF-043, RF-044, RNF-015, RN-008, RN-010, RN-017 e RN-018
- [Integração Brapi](../../integracoes/integracao_api_bolsa_brasil.md)
- [ESPEC-09 - Catálogo de Ativos](../../requisitos/especs/ESPEC_09_catalogo-de-ativos.md)
- [Documentação Brapi](https://brapi.dev/docs)
