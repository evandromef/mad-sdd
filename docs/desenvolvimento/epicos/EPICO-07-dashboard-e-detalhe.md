# EPICO-07 - Dashboard e Detalhe do Ativo

## Objetivo

Exibir a situacao consolidada da carteira e o detalhamento operacional de cada ativo.

## Requisitos relacionados

- RF-027 a RF-040
- RN-001, RN-010, RN-012, RN-013, RN-014, RN-015, RN-016
- ESPEC-07, ESPEC-08

## Escopo

- Dashboard da carteira selecionada.
- Valor atual, custo, P&L e rentabilidade.
- Alocacao por categoria e ativo.
- Maior posicao.
- Evolucao do custo de aquisicao por mes ou ano.
- Total de proventos filtrado.
- Detalhe do ativo.
- Historicos e acoes contextuais.

## Fora do escopo

- Graficos obrigatorios.
- Historico de cotacoes.
- Evolucao historica do valor de mercado.
- Visao consolidada de todas as carteiras.

## Sprints relacionadas

- Sprint 7
- Sprint 8
- Sprint 9

## Criterios de conclusao

- Dashboard respeita a carteira selecionada.
- Cotacao ausente nao vira zero.
- Rentabilidade fica indisponivel quando Custo Total e zero.
- Detalhe exibe indicadores, historicos, proventos, notas e acoes contextuais.

## Riscos e dependencias

- Depende de posicao, cotacoes, proventos e historicos.
- Desempenho deve atender carteira com ate 50 ativos e 1.000 operacoes.

## Status

- Planejado
