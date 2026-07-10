# Roadmap do MVP

## Visao geral

O plano esta organizado em Sprint 0 mais 9 sprints de desenvolvimento. A ordem prioriza fundacao tecnica, seguranca, catalogo, posicao materializada, lancamentos financeiros, cotacoes e visualizacoes.

| Sprint | Tema | Objetivo |
| --- | --- | --- |
| Sprint 0 | Fundacao | Preparar estrutura tecnica, padroes, CI e base do projeto. |
| Sprint 1 | Conta e acesso | Implementar autenticacao, sessao, recuperacao e exclusao de conta. |
| Sprint 2 | Catalogo de ativos | Integrar catalogo Brapi e impedir cadastro manual de ativos. |
| Sprint 3 | Carteiras e base da posicao | Implementar carteiras e fundacao da posicao materializada. |
| Sprint 4 | Operacoes e motor de posicao | Implementar compra, venda, subscricao e reprocessamento cronologico. |
| Sprint 5 | Bonificacoes e eventos | Implementar ajustes de quantidade sem alterar custo total. |
| Sprint 6 | Proventos e notas | Implementar proventos, consolidacoes e notas por ativo. |
| Sprint 7 | Cotacoes e indicadores | Implementar cotacao atual, jobs e indicadores financeiros. |
| Sprint 8 | Dashboard e detalhe | Entregar dashboard completo e tela de detalhe do ativo. |
| Sprint 9 | Hardening MVP | Estabilizar, testar, validar performance e preparar homologacao. |

## Dependencias criticas

- ESPEC-01 antes de qualquer funcionalidade autenticada.
- ESPEC-09 antes de formularios que selecionam ativos.
- Motor de posicao antes de dashboard, detalhe do ativo e regras de venda.
- Cotações antes de valor atual, P&L, alocacao e maior posicao.

## Pendencias de definicao

- Politica minima de complexidade de senha.
- Validade do token de confirmacao de e-mail.
- Limite de texto para nome de carteira, descricoes e notas.
- Regra temporal exata para indicar cotacao desatualizada.
