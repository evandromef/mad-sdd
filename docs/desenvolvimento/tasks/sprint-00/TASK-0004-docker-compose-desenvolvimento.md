# TASK-0004 - PostgreSQL local e conteineres de entrega

## Objetivo

Padronizar o PostgreSQL 17 local via Docker Compose e preparar conteineres independentes de frontend e backend para homologacao e producao.

## Contexto

A Sprint 0 precisa reduzir variacao de ambiente e permitir que o projeto seja executado de forma repetivel. No desenvolvimento, frontend e backend executam nativamente com a stack oficial, enquanto o Docker Compose fornece somente o PostgreSQL 17. Os conteineres das aplicacoes sao artefatos de entrega para homologacao e producao.

## Requisitos relacionados

- RF: N/A
- RN: N/A
- RNF: RNF-001, RNF-009, RNF-017
- ESPEC: N/A
- ADR: ADR-001, ADR-002

## Escopo tecnico

- Criar Dockerfile de entrega do backend em `codebase/backend/`.
- Criar Dockerfile de entrega do frontend em `codebase/frontend/`.
- Manter somente o PostgreSQL 17 no `codebase/docker-compose.yml` de desenvolvimento.
- Executar frontend e backend nativamente no desenvolvimento.
- Definir variaveis de ambiente locais sem segredos reais.
- Documentar portas e comandos de uso.

## Criterios de aceite

- `docker compose up -d postgres` sobe o banco local planejado.
- PostgreSQL 17 fica acessivel na porta documentada.
- Frontend e backend executam nativamente com os comandos documentados.
- As imagens de frontend e backend sao construidas independentemente para homologacao e producao.
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

- Subida local do PostgreSQL 17 via Docker Compose.
- Execucao nativa e testes automatizados de frontend e backend.
- Build independente das imagens de entrega.

## Dependencias

- TASK-0001.
- TASK-0002.
- TASK-0003.

## Status

- [ ] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [x] Concluida

## Notas de implementacao

- O Docker Compose de desenvolvimento contem somente o PostgreSQL 17.
- Frontend e backend executam nativamente no desenvolvimento.
- Os Dockerfiles das aplicacoes destinam-se a homologacao e producao e nao integram o Compose local.
- Backend empacotado em build multi-stage com Maven e executado em imagem JRE Java 25.
- Frontend compilado em build multi-stage com Node.js 24 e servido por Nginx com fallback para rotas da SPA.
- Contextos de build excluem artefatos locais, dependencias instaladas e metadados de IDE.
- A porta publicada do PostgreSQL pode ser alterada por `MAD_DB_PORT`.
- As portas locais das aplicacoes usam os mecanismos nativos: `SERVER_PORT` no Spring Boot e `--port` no Angular CLI.
- O segredo local `MAD_DB_PASSWORD` permanece obrigatorio no arquivo `.env` ignorado pelo Git; nenhum valor real foi versionado.
- O frontend entregue pelo Nginx usa a porta interna 80; o backend usa a porta interna 8080. As portas publicas sao configuradas pela plataforma.
- A implementacao inicial integrou os tres servicos no Compose e validou hot reload, endpoints, migrations e imagens.
- Ajuste de revisao solicitado em 2026-07-24: removidos frontend e backend do ambiente Docker de desenvolvimento, preservando suas imagens apenas para entrega.
- Revalidacao do ajuste: Compose aprovado e PostgreSQL 17 iniciado ate o estado `healthy`; o conteiner e a rede temporarios foram removidos, preservando o volume.
- Testes automatizados aprovados: 4 testes backend e 4 testes frontend, sem falhas.
- Imagens de entrega do backend e do frontend construidas com sucesso.
- TASK-0004 aprovada diretamente pelo desenvolvedor e concluida em 2026-07-24.
