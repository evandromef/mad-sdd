# TASK-0001 - Scaffold do backend Spring Boot

## Objetivo

Criar a base da API backend em Spring Boot, alinhada a arquitetura definida para o monolito modular do MAD.

## Contexto

A Sprint 0 prepara a fundacao tecnica para que as funcionalidades das sprints seguintes sejam implementadas de forma segura, testavel e repetivel. O backend deve nascer dentro de `codebase/backend/`, com a estrutura minima de projeto, organizacao de pacotes e validacao basica de execucao.

## Requisitos relacionados

- RF: N/A
- RN: N/A
- RNF: RNF-001, RNF-009, RNF-017
- ESPEC: N/A
- ADR: ADR-001

## Escopo tecnico

- Criar projeto backend com Java 25 e Spring Boot 3 em `codebase/backend/`.
- Configurar estrutura inicial de pacotes para monolito modular.
- Criar endpoint tecnico simples para validacao de subida da API.
- Configurar build, execucao local e teste automatizado base.
- Preparar dependencias iniciais coerentes com arquitetura: Web, Validation, Security, Data JPA, Flyway, Actuator e OpenAPI quando aplicavel.

## Criterios de aceite

- Backend compila localmente.
- Aplicacao sobe em ambiente local.
- Existe teste automatizado base executando com sucesso.
- Todo codigo do backend fica dentro de `codebase/backend/`.
- Estrutura inicial de pacotes segue a decisao do ADR-001.
- Nenhuma regra funcional de negocio e implementada nesta task.

## Arquivos previstos

- `codebase/backend/`
- `codebase/backend/pom.xml`
- `codebase/backend/src/main/`
- `codebase/backend/src/test/`
- `README.md`

## Testes previstos

- Teste de contexto da aplicacao.
- Teste simples do endpoint tecnico, se criado como endpoint HTTP.

## Dependencias

- Java 25 disponivel no ambiente de desenvolvimento.
- Definicao da ferramenta de build do backend.

## Status

- [x] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [x] Concluida

## Notas de implementacao

- Nao implementar endpoints de negocio nesta task.
- Evitar dependencias nao previstas na arquitetura sem novo registro de decisao.
- Implementado com Spring Boot 3.5.15 e Java 25.
- Teste executado com sucesso em 2026-07-10 usando o JDK de `/usr/lib/jvm/java-25-openjdk-amd64`.
