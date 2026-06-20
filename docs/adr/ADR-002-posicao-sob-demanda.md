# ADR-002 - Posicao Calculada Sob Demanda

## Status

Aceita.

## Decisao

Nao existira tabela `posicoes`. A posicao atual sera calculada pelo `PosicoesService` a partir de operacoes e eventos corporativos em ordem cronologica.

## Contexto

Posicao materializada exige sincronizacao apos cada edicao, exclusao ou evento. Isso aumenta risco de divergencia em um dominio financeiro sensivel.

## Consequencias

- Recalculo fica deterministico e auditavel.
- Queries podem custar mais, mas o MVP tem limite pequeno de ativos.
- Frontend usa cache HTTP de 60s em endpoints de posicao/dashboard.
- Se o volume crescer, uma materializacao auditada pode ser reavaliada.

