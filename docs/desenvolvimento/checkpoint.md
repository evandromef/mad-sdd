# Checkpoint

Memoria de trabalho volatil do plano de desenvolvimento.

## Onde paramos

- Branch `codex/sprint-00-fundacao` criada.
- Sprint 0 iniciada.
- TASK-0001 concluida com build e teste automatizado aprovados no Java 25.
- TASK-0002 aprovada diretamente pelo desenvolvedor e concluida.
- Frontend Angular 21 criado em `codebase/frontend/` com PrimeNG, Tailwind CSS, shell inicial e testes base.
- Ajuste de revisao P2 aplicado na TASK-0002: teste base alinhado a Vitest com Angular Testing Library.
- Achado P2 do README resolvido, validacao visual em 360px aprovada pelo desenvolvedor e textos visiveis corrigidos com acentuacao em portugues.
- Corrigido o recorte da tabela de posicoes em larguras intermediarias com um contêiner de rolagem horizontal dedicado.
- Configuracoes TypeScript da aplicacao e dos testes ajustadas com `rootDir` explicito para compatibilidade com TypeScript 6.
- Tabela de posicoes migrada para HTML tabular nativo com teste de semantica acessivel.
- Testes do `AppShell` colocados junto ao componente; `app.spec.ts` permanece focado no componente raiz.

## Proximo passo imediato

- Preparar o inicio da TASK-0003, mantendo-a como `Planejada` ate autorizacao explicita para implementacao.

## Bloqueios

- Nenhum bloqueio ativo.

## Decisoes recentes

- O arquivo de memoria volatil se chama `checkpoint.md`.
- Tasks da Sprint 0 foram criadas apos aprovacao explicita do usuario.
- Todo codigo da aplicacao deve ficar dentro de `codebase/`; documentacao e logs permanecem em `docs/` e `logs/`.
- O plano usara 9 sprints efetivas mais a Sprint 0.
- Spring Boot 3.5.x sera usado por oferecer suporte ao Java 25 sem migrar para Spring Boot 4.
- O fluxo de revisao e aprovacao se aplica somente ao desenvolvimento das tasks das sprints.
- A TASK-0001 esta aprovada e concluida.
- A TASK-0002 foi aprovada diretamente e esta concluida.
- Testes de componente do frontend devem usar Angular Testing Library sobre Vitest.
- A TASK-0006 deve formalizar e aplicar a convencao: Tailwind para layout/espacamento/responsividade, PrimeNG para componentes e CSS local apenas para estilos especificos; o scaffold atual ainda concentra layout nos CSS dos componentes.

## Contexto importante

- Toda implementacao deve seguir as ESPECs em `docs/requisitos/especs/`.
- Java 25 e javac 25 estao configurados como padrao do shell e sao usados pelo Maven.
- Codigo de backend, frontend, Docker, E2E e arquivos operacionais da aplicacao devem ser criados em `codebase/`.
- O motor de posicao materializada e uma dependencia central para operacoes, bonificacoes, eventos, dashboard e detalhe do ativo.
- Preco medio nao deve ser calculado, persistido nem exibido.

## Arquivos em andamento

- `docs/desenvolvimento/`
- `docs/desenvolvimento/tasks/sprint-00/`
- `codebase/`
- `codebase/frontend/`

## Ultima atualizacao

- Data/hora: 2026-07-20 20:51 America/Sao_Paulo
- Responsavel: Codex
