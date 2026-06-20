# Backend Architecture

## Principios

- Java 25 LTS, Spring Boot 3.
- Camadas: Controller -> Service -> Repository.
- Entities JPA nao devem vazar para a API.
- DTOs de request/response por endpoint.
- MapStruct recomendado para mapeamentos simples.
- BigDecimal para dinheiro e quantidades fracionarias.

## Pastas

```text
backend/src/main/java/.../
  auth/
  carteira/
  ativo/
  operacao/
  evento/
  posicao/
  provento/
  cotacao/
  dashboard/
  shared/
```

## Erros

- `BusinessException` deve conter codigo `ERR-XXX`.
- `GlobalExceptionHandler` converte excecoes em `ErrorResponse`.
- Bean Validation retorna `ERR-011`.
- Alteracoes retroativas que deixem a linha do tempo com quantidade negativa devem retornar `ERR-013`.

## Posicoes

- `PosicaoDTO` deve ser `record`.
- `PosicoesService` calcula sob demanda com historico de operacoes e eventos.
- Nao criar Entity `Posicao`.
- O calculo deve ordenar por `data`; no mesmo dia, eventos corporativos antes de operacoes; depois `data_criacao` e `id`.
- Se a quantidade final for zero, o Custo Total final deve ser `0.00`.
- Listagens devem omitir posicoes zeradas por padrao e aceitar `incluirZeradas=true`.
- Criacao, edicao e exclusao de operacoes/eventos devem validar o historico recalculado antes de confirmar a transacao.

## Transacoes

- Escritas de operacao, evento, provento e carteira devem ser transacionais.
- Calculo de posicao pode ser read-only.
- Fluxos de edicao/exclusao de operacao ou evento devem recalcular dentro da transacao e fazer rollback se houver `ERR-013`.

## Cotacoes

- `BrapiClient` encapsula chamadas externas.
- `CotacaoService` decide fallback, upsert e snapshot mensal.
- `CotacaoScheduler` roda apos fechamento do mercado, configurado para America/Sao_Paulo.
