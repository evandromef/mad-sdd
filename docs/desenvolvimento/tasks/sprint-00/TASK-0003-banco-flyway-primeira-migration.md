# TASK-0003 - Banco local, Flyway e primeira migration

## Objetivo

Configurar PostgreSQL local e Flyway para permitir evolucao versionada do schema desde o inicio do projeto.

## Contexto

O MAD depende de persistencia relacional, transacoes ACID, UUIDs, tipos numericos precisos e migracoes reprodutiveis. A Sprint 0 deve garantir que o banco rode do zero e que a API em `codebase/backend/` esteja preparada para evoluir o modelo de dados nas proximas sprints.

## Requisitos relacionados

- RF: N/A
- RN: RN-001, RN-002, RN-003
- RNF: RNF-001, RNF-012, RNF-013, RNF-017
- ESPEC: N/A
- ADR: ADR-002

## Escopo tecnico

- Configurar conexao local da API com PostgreSQL.
- Configurar Flyway no backend em `codebase/backend/`.
- Criar primeira migration tecnica minima.
- Definir convencao de migrations.
- Preparar configuracao para Testcontainers nos testes de integracao.

## Criterios de aceite

- Banco local sobe em ambiente de desenvolvimento.
- Flyway executa as migrations do zero sem erro.
- API conecta no banco local usando variaveis de ambiente ou perfil local.
- Migration inicial nao implementa regra funcional fora do escopo da Sprint 0.

## Arquivos previstos

- `codebase/backend/src/main/resources/application*.yml`
- `codebase/backend/src/main/resources/db/migration/`
- `codebase/docker-compose.yml`
- `README.md`

## Testes previstos

- Teste de integracao simples validando contexto com PostgreSQL via Testcontainers.
- Execucao local de migrations em banco limpo.

## Dependencias

- TASK-0001.
- Docker disponivel no ambiente local.

## Status

- [x] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [ ] Concluida

## Notas de implementacao

- Usar `NUMERIC` e UUID conforme ADR-002 quando tabelas de negocio forem criadas em sprints futuras.
- Nao criar preco medio em nenhuma estrutura persistida.
