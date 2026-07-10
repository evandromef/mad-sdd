# EPICO-05 - Posicao e Operacoes

## Objetivo

Registrar compras, vendas e subscricoes mantendo quantidade atual e Custo de Aquisicao Total de forma transacional.

## Requisitos relacionados

- RF-010 a RF-014, RF-036
- RN-001, RN-002, RN-003, RN-007, RN-008, RN-012
- RNF-005, RNF-013, RNF-016, RNF-017
- ESPEC-03

## Escopo

- Entidade `operacao`.
- Entidade `posicao_ativo`.
- Compra, venda e subscricao.
- Edicao e exclusao de operacoes.
- Reprocessamento cronologico por carteira/ativo.
- Validacao de venda pela quantidade historica disponivel.

## Fora do escopo

- Execucao real de ordens.
- Calculo de IR.
- Preco medio.

## Sprints relacionadas

- Sprint 3
- Sprint 4

## Criterios de conclusao

- Operacoes atualizam posicao na mesma transacao.
- Venda superior a quantidade disponivel e bloqueada.
- Venda reduz Custo Total proporcionalmente.
- Posicao zerada zera Custo Total.
- Preco medio nao e calculado nem persistido.

## Riscos e dependencias

- Lancamentos retroativos podem invalidar eventos posteriores.
- Precisao decimal deve ser preservada sem arredondamento intermediario.

## Status

- Planejado
