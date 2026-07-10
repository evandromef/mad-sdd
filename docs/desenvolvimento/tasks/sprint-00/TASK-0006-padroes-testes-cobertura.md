# TASK-0006 - Padroes de testes e cobertura

## Objetivo

Definir e configurar os padroes iniciais de testes automatizados e cobertura para backend, frontend e e2e.

## Contexto

O RNF-017 exige testes unitarios, de integracao e end-to-end automatizados com cobertura minima de 80%. A Sprint 0 deve preparar comandos, ferramentas e exemplos minimos dentro de `codebase/` para que as sprints seguintes mantenham esse padrao.

## Requisitos relacionados

- RF: N/A
- RN: N/A
- RNF: RNF-017
- ESPEC: N/A
- ADR: ADR-001, ADR-002

## Escopo tecnico

- Configurar testes unitarios do backend em `codebase/backend/`.
- Configurar testes de integracao do backend com Testcontainers em `codebase/backend/`.
- Configurar testes do frontend com Vitest e Angular Testing Library em `codebase/frontend/`.
- Preparar estrutura inicial para Playwright em `codebase/e2e/` ou estrutura equivalente dentro de `codebase/`.
- Configurar relatorios de cobertura quando aplicavel.
- Documentar comandos padrao de teste.

## Criterios de aceite

- Comando de testes do backend executa com sucesso.
- Comando de testes do frontend executa com sucesso.
- Estrutura de e2e esta preparada para evolucao posterior.
- Relatorios de cobertura sao gerados ou comando equivalente fica documentado.
- Falhas de teste retornam codigo de erro adequado para CI.

## Arquivos previstos

- `codebase/backend/src/test/`
- `codebase/frontend/src/**/*.spec.ts`
- `codebase/frontend/`
- `codebase/e2e/` ou configuracao equivalente de Playwright dentro de `codebase/`
- `README.md`
- `.github/workflows/`

## Testes previstos

- Testes unitarios base.
- Teste de integracao base.
- Teste e2e smoke, se a stack local ja permitir.

## Dependencias

- TASK-0001.
- TASK-0002.
- TASK-0003.

## Status

- [x] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [ ] Concluida

## Notas de implementacao

- Cobertura minima de 80% deve ser preparada na fundacao e aplicada com rigor crescente nas funcionalidades.
