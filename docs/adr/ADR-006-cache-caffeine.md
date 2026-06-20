# ADR-006 - Cache Caffeine no MVP

## Status

Aceita.

## Decisao

Usar cache in-memory com Caffeine para cotacoes no backend.

## Consequencias

- Evita infraestrutura extra no MVP.
- Cache e perdido em restart ou cold start.
- Redis fica reservado para fase futura com multiplas instancias.

