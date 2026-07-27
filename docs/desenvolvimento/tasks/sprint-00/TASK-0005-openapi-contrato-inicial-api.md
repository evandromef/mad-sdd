# TASK-0005 - OpenAPI e contrato inicial da API

## Objetivo

Configurar documentacao OpenAPI para a API REST versionada sob `/api/v1`.

## Contexto

O projeto exige API REST JSON documentada por OpenAPI. A fundacao deve criar o mecanismo de documentacao e validacao no backend em `codebase/backend/` antes da implementacao das funcionalidades de negocio.

## Requisitos relacionados

- RF: N/A
- RN: N/A
- RNF: RNF-001, RNF-017
- ESPEC: N/A
- ADR: ADR-001

## Escopo tecnico

- Configurar OpenAPI no backend em `codebase/backend/`.
- Definir metadados iniciais da API.
- Garantir versionamento sob `/api/v1`.
- Expor documentacao em ambiente local.
- Preparar base para validacao do contrato no CI.

## Criterios de aceite

- Documento OpenAPI e gerado ou exposto localmente.
- Endpoints tecnicos iniciais aparecem no contrato quando aplicavel.
- Caminhos publicos seguem o prefixo `/api/v1`.
- Configuracao permite futura validacao automatizada no pipeline.

## Arquivos previstos

- `codebase/backend/pom.xml`
- `codebase/backend/src/main/`
- `codebase/backend/src/test/`
- `codebase/backend/src/main/resources/application*.yml`
- `docs/api/openapi.yaml`
- `README.md`

## Testes previstos

- Teste simples de disponibilidade do documento OpenAPI.
- Validacao manual do contrato gerado.

## Dependencias

- TASK-0001.

## Status

- [ ] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [x] Concluida

## Notas de implementacao

- Evitar documentar endpoints de negocio antes das ESPECs funcionais aprovadas.
- Swagger UI e o documento OpenAPI dinamico devem ficar expostos somente no perfil `development`.
- A validacao efetiva do contrato no pipeline pertence a TASK-0007; esta task prepara testes executaveis pelo Maven.
- Springdoc OpenAPI `2.8.17` configurado por ser a linha compativel com Spring Boot 3.5.
- OpenAPI e Swagger UI ficam desabilitados em `application.yml` e sao habilitados explicitamente apenas em `application-development.yml`.
- O contrato versionado inicial esta em `docs/api/openapi.yaml` e documenta somente `GET /api/v1/system/status`.
- A resposta tecnica passou a usar o record `SystemStatusResponse`, evitando schema generico no contrato.
- A validacao identificou que o endpoint nao declarava o tipo produzido; `application/json` foi explicitado para alinhar implementacao, documento dinamico e contrato versionado.
- Testes automatizados comprovam que OpenAPI e Swagger UI ficam publicos somente quando `development` e o unico perfil ativo.
- Fora dessa condicao, as rotas usam `denyAll()` e handlers especificos de autenticacao ausente e acesso negado retornam HTTP 404, inclusive para usuarios autenticados e quando Springdoc e habilitado externamente.
- Rotas protegidas que nao pertencem a documentacao preservam a resposta HTTP 401 para requisicoes anonimas.
- Validacao final: `mvn test` executou 8 testes com sucesso, sem falhas, erros ou testes ignorados.

## Itens de revisao

### [P2] Restringir a autorizacao da documentacao ao perfil development

- Evidencia: `SecurityConfig` autoriza incondicionalmente `/v3/api-docs/**`, `/swagger-ui.html` e `/swagger-ui/**`, independentemente do perfil Spring ativo.
- Impacto: embora OpenAPI e Swagger UI estejam desabilitados por padrao, uma sobrescrita externa de `springdoc.api-docs.enabled` ou `springdoc.swagger-ui.enabled` em homologacao ou producao tornaria a documentacao publicamente acessivel sem autenticacao.
- Divergencia: o comportamento contradiz a decisao da TASK-0005 de expor a documentacao dinamica somente no perfil `development`.
- Correcao esperada: condicionar as permissoes publicas das rotas de documentacao ao perfil `development`, mantendo as rotas tecnicas publicas independentes dessa condicao.
- Teste de regressao esperado: comprovar que uma configuracao nao-development com Springdoc habilitado externamente nao torna as rotas de documentacao publicas, preservando o acesso no perfil `development`.
- Correcao aplicada: o `SecurityConfig` libera publicamente as rotas de documentacao somente quando o perfil `development` esta ativo. Em qualquer outro perfil, as rotas exigem autenticacao, independentemente de o Springdoc estar habilitado.
- Teste de regressao: `OpenApiNonDevelopmentOverrideIntegrationTest` ativa o perfil `production`, habilita OpenAPI e Swagger UI externamente e comprova HTTP 404 para usuarios anonimos e autenticados.
- Revalidacao: os testes preservam a exposicao publica em `development`, ocultam a documentacao com HTTP 404 fora dele e falham fechado quando `development` e combinado com outro perfil.
- Status do achado: corrigido, aguardando revisao.
