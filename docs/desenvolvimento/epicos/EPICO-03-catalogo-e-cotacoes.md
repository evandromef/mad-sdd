# EPICO-03 - Catalogo e Cotacoes

## Objetivo

Manter catalogo mestre de Acoes e FIIs e atualizar cotacoes atuais dos ativos referenciados em carteiras.

## Requisitos relacionados

- RF-034, RF-041 a RF-044
- RN-008, RN-010, RN-017, RN-018
- RNF-015, RNF-017
- ESPEC-09
- ADR-004

## Escopo

- Cliente Brapi.
- Carga inicial do catalogo.
- Atualizacao periodica do catalogo.
- Busca de ativos elegiveis.
- Cotacao atual por ativo referenciado.
- Job diario de cotacoes com uma chamada por ticker.

## Fora do escopo

- Historico de cotacoes.
- Atualizacao sob demanda.
- Suporte a ativos fora de Acoes e FIIs.

## Sprints relacionadas

- Sprint 2
- Sprint 7

## Criterios de conclusao

- Usuario seleciona apenas ativos do catalogo.
- Ativos historicos indisponiveis sao preservados.
- Cotacoes validas alimentam indicadores sem substituir ausencias por zero.

## Riscos e dependencias

- Endpoint exato de catalogo da Brapi.
- Limites efetivos do plano contratado.
- Regra temporal para cotacao desatualizada.

## Status

- Planejado
