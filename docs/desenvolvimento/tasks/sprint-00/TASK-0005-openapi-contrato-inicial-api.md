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

- `codebase/backend/src/main/`
- `codebase/backend/src/main/resources/application*.yml`
- `docs/`
- `.github/workflows/`

## Testes previstos

- Teste simples de disponibilidade do documento OpenAPI.
- Validacao manual do contrato gerado.

## Dependencias

- TASK-0001.

## Status

- [x] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [ ] Concluida

## Notas de implementacao

- Evitar documentar endpoints de negocio antes das ESPECs funcionais aprovadas.
