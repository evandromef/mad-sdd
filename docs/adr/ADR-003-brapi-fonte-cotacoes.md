# ADR-003 - Brapi como Fonte de Cotacoes no MVP

## Status

Aceita.

## Decisao

Usar Brapi como unica fonte externa de cotacoes no MVP.

## Consequencias

- Menor complexidade de integracao.
- Falhas da Brapi bloqueiam cadastro de novo ativo.
- Falhas de atualizacao preservam ultima cotacao conhecida e sinalizam desatualizacao.

