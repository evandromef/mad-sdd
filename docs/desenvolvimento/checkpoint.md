# Checkpoint

Memoria de trabalho volatil do plano de desenvolvimento.

## Onde paramos

- Branch `codex/sprint-00-fundacao` criada.
- Sprint 0 iniciada.
- TASK-0001 concluida com build e teste automatizado aprovados no Java 25.
- TASK-0002 implementada e colocada em revisao.
- Frontend Angular 21 criado em `codebase/frontend/` com PrimeNG, Tailwind CSS, shell inicial e testes base.

## Proximo passo imediato

- Aguardar revisao/aprovacao direta da TASK-0002 antes de marca-la como concluida ou iniciar nova task.

## Bloqueios

- TASK-0002 aguarda revisao/aprovacao direta do usuario para conclusao.

## Decisoes recentes

- O arquivo de memoria volatil se chama `checkpoint.md`.
- Tasks da Sprint 0 foram criadas apos aprovacao explicita do usuario.
- Todo codigo da aplicacao deve ficar dentro de `codebase/`; documentacao e logs permanecem em `docs/` e `logs/`.
- O plano usara 9 sprints efetivas mais a Sprint 0.
- Spring Boot 3.5.x sera usado por oferecer suporte ao Java 25 sem migrar para Spring Boot 4.
- O fluxo de revisao e aprovacao se aplica somente ao desenvolvimento das tasks das sprints.
- A TASK-0001 esta aprovada e concluida.
- A TASK-0002 esta em revisao.

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

- Data/hora: 2026-07-14 12:18 America/Sao_Paulo
- Responsavel: Codex
