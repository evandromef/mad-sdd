# TASK-0002 - Scaffold do frontend Angular

## Objetivo

Criar a base da aplicacao frontend em Angular, com estrutura inicial preparada para as telas do MVP.

## Contexto

O frontend deve ser uma aplicacao independente da API, conforme arquitetura do projeto. A base deve ficar em `codebase/frontend/` e permitir evolucao por rotas, componentes standalone, services, formularios reativos e padrao visual consistente.

## Requisitos relacionados

- RF: N/A
- RN: N/A
- RNF: RNF-001, RNF-010, RNF-011, RNF-017
- ESPEC: N/A
- ADR: ADR-001

## Escopo tecnico

- Criar projeto Angular 21 com TypeScript em `codebase/frontend/`.
- Configurar PrimeNG e Tailwind CSS.
- Definir estrutura inicial de pastas para core, shared, layout e features.
- Criar shell visual minimo da aplicacao.
- Configurar teste automatizado base.

## Criterios de aceite

- Frontend compila localmente.
- Aplicacao sobe em ambiente local.
- Teste base executa com sucesso.
- Todo codigo do frontend fica dentro de `codebase/frontend/`.
- Estrutura inicial favorece components standalone, services e reactive forms.
- Interface inicial e responsiva em largura minima de 360px.

## Arquivos previstos

- `codebase/frontend/`
- `codebase/frontend/package.json`
- `codebase/frontend/src/`
- `codebase/frontend/angular.json`
- `codebase/frontend/tailwind.config.*`
- `README.md`

## Testes previstos

- Teste base de componente.
- Validacao manual de subida local.

## Dependencias

- Node.js 24 LTS disponivel no ambiente de desenvolvimento.
- Angular CLI compativel com Angular 21.

## Status

- [ ] Planejada
- [ ] Em andamento
- [x] Em revisao
- [ ] Concluida

## Notas de implementacao

- Nao criar landing page.
- A primeira tela deve funcionar como base real da aplicacao, mesmo que ainda sem funcionalidades de negocio.
