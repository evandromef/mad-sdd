# ADR-004 - Snapshot Mensal de Cotacao

## Status

Aceita.

## Decisao

Armazenar um snapshot mensal por ativo em `cotacoes_mensais`.

## Consequencias

- Grafico de evolucao patrimonial pode ser gerado sem manter serie diaria completa.
- Dentro do mes atual, o snapshot pode ser sobrescrito a cada atualizacao.

