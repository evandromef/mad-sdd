# ADR-008 - OpenAPI como Contrato

## Status

Aceita.

## Decisao

O contrato REST deve ser mantido em `docs/api/openapi.yaml` antes da implementacao de controllers e clients.

## Consequencias

- Backend e frontend compartilham linguagem comum.
- Mudancas de API devem atualizar spec e testes antes do codigo.

