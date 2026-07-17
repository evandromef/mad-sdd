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
- Configurar o Mockito como agente Java no Maven Surefire, usando a versao gerenciada pelo Spring Boot e preservando composicao com outros argumentos de JVM, como o agente do JaCoCo.
- Configurar testes do frontend com Vitest e Angular Testing Library em `codebase/frontend/`.
- Preparar estrutura inicial para Playwright em `codebase/e2e/` ou estrutura equivalente dentro de `codebase/`.
- Configurar relatorios de cobertura quando aplicavel.
- Documentar comandos padrao de teste.
- Documentar e validar o padrao de estilizacao do frontend: Tailwind CSS para layout, espacamento e responsividade; PrimeNG para componentes de interface; CSS de componente apenas para estilos especificos sem utilitario adequado ou para comportamento intrinseco do elemento.
- Revisar o scaffold do frontend e migrar os estilos de layout que estejam em CSS de componente para utilitarios Tailwind, sem misturar refatoracao visual com alteracao funcional.

## Criterios de aceite

- Comando de testes do backend executa com sucesso.
- Testes do backend executam no Java 25 sem o aviso de self-attachment do inline mock maker do Mockito.
- Comando de testes do frontend executa com sucesso.
- Estrutura de e2e esta preparada para evolucao posterior.
- Relatorios de cobertura sao gerados ou comando equivalente fica documentado.
- Falhas de teste retornam codigo de erro adequado para CI.
- Convencao de uso de Tailwind, PrimeNG e CSS de componente documentada em `docs/desenvolvimento/padroes/frontend.md`.
- Shell e dashboard iniciais seguem a convencao, mantendo em CSS proprio somente estilos especificos cuja permanencia esteja justificada.

## Arquivos previstos

- `codebase/backend/src/test/`
- `codebase/backend/pom.xml`
- `codebase/frontend/src/**/*.spec.ts`
- `codebase/frontend/`
- `codebase/e2e/` ou configuracao equivalente de Playwright dentro de `codebase/`
- `README.md`
- `.github/workflows/`
- `docs/desenvolvimento/padroes/frontend.md`

## Testes previstos

- Testes unitarios base.
- Teste de integracao base.
- Execucao de `mvn test` no Java 25 para confirmar o carregamento do Mockito por `-javaagent` sem self-attachment dinamico.
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
- A configuracao do agente Mockito deve seguir a documentacao oficial, sem duplicar ou sobrescrever a versao gerenciada pelo Spring Boot e sem impedir a instrumentacao de cobertura do JaCoCo.
- Convencao de frontend a aplicar: Tailwind para grid, flex, espacamento, dimensoes e breakpoints; PrimeNG para componentes prontos; CSS local para semantica visual especifica, estados particulares e comportamentos sem equivalente claro em utilitarios.
- Levantamento previo do scaffold em 2026-07-17: PrimeNG esta configurado com o tema Aura e utilizado no botao de nova operacao e nas tags de categoria; Tailwind esta configurado e importado, mas nenhuma classe utilitaria e usada nos templates.
- Levantamento previo do scaffold em 2026-07-17: `app-shell.css` e `dashboard-page.css` concentram grid, flex, gaps, paddings, dimensoes e breakpoints, portanto a convencao arquitetural ainda nao e atendida na parte de layout.
- Estilos globais de reset, tipografia base e largura minima em `src/styles.css` estao no escopo adequado; estilos tabulares especificos, como `border-collapse` e overflow do contêiner, podem permanecer locais quando isso produzir uma solucao mais clara.
