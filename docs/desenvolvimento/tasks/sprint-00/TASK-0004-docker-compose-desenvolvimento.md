# TASK-0004 - Docker Compose de desenvolvimento

## Objetivo

Padronizar o ambiente local de desenvolvimento com Docker Compose para backend, frontend e PostgreSQL.

## Contexto

A Sprint 0 precisa reduzir variacao de ambiente e permitir que o projeto seja executado de forma repetivel. O Docker local deve apoiar desenvolvimento, testes manuais e validacao basica da stack, mantendo os arquivos executaveis da aplicacao dentro de `codebase/`.

## Requisitos relacionados

- RF: N/A
- RN: N/A
- RNF: RNF-001, RNF-009, RNF-017
- ESPEC: N/A
- ADR: ADR-001, ADR-002

## Escopo tecnico

- Criar Dockerfile do backend em `codebase/backend/`.
- Criar Dockerfile do frontend em `codebase/frontend/`.
- Criar ou ajustar `codebase/docker-compose.yml` para servicos locais.
- Definir variaveis de ambiente locais sem segredos reais.
- Documentar portas e comandos de uso.

## Criterios de aceite

- `docker compose up` sobe a stack local planejada.
- Backend, frontend e PostgreSQL ficam acessiveis nas portas documentadas.
- Nao ha segredos reais versionados.
- O ambiente local permite executar migrations no banco.
- Arquivos operacionais da aplicacao ficam dentro de `codebase/`.

## Arquivos previstos

- `codebase/backend/Dockerfile`
- `codebase/frontend/Dockerfile`
- `codebase/docker-compose.yml`
- `codebase/.env.example`
- `README.md`

## Testes previstos

- Subida local da stack via Docker Compose.
- Validacao manual dos endpoints tecnicos e da tela inicial.

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

- Separar configuracoes locais de configuracoes futuras de homologacao/producao.
- Evitar acoplamento entre containers que dificulte execucao isolada de backend ou frontend.
