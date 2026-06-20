# ADR-007 - Erros Catalogados

## Status

Aceita.

## Decisao

Toda excecao de negocio deve mapear para um codigo do catalogo `docs/api/error-catalog.md`.

## Consequencias

- Frontend nao depende de textos soltos.
- Testes podem validar codigo de erro e status HTTP.

