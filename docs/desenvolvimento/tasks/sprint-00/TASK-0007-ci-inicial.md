# TASK-0007 - CI inicial

## Objetivo

Criar pipeline inicial de integracao continua para validar build, testes, migrations e contrato basico da aplicacao.

## Contexto

O pipeline inicial deve garantir que a base tecnica em `codebase/` continue compilando e que erros simples sejam detectados antes da integracao de funcionalidades de negocio.

## Requisitos relacionados

- RF: N/A
- RN: N/A
- RNF: RNF-001, RNF-017
- ESPEC: N/A
- ADR: ADR-001, ADR-002

## Escopo tecnico

- Criar workflow GitHub Actions.
- Executar build e testes do backend em `codebase/backend/`.
- Executar build e testes do frontend em `codebase/frontend/`.
- Validar migrations em PostgreSQL 17 de CI ou Testcontainers.
- Preparar etapa de validacao OpenAPI quando disponivel.

## Criterios de aceite

- Pipeline executa em push e pull request.
- Falha de build ou teste quebra o pipeline.
- Migrations sao validadas em ambiente limpo.
- O workflow nao depende de segredos reais para execucao basica.

## Arquivos previstos

- `.github/workflows/ci.yml`
- `codebase/backend/`
- `codebase/frontend/`
- `README.md`

## Testes previstos

- Execucao local dos mesmos comandos usados pelo CI.
- Validacao do workflow em branch da sprint.

## Dependencias

- TASK-0001.
- TASK-0002.
- TASK-0003.
- TASK-0005.
- TASK-0006.

## Status

- [x] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [ ] Concluida

## Notas de implementacao

- Deploy automatico nao faz parte desta task.
- Homologacao e producao devem ser tratados em etapa posterior.
