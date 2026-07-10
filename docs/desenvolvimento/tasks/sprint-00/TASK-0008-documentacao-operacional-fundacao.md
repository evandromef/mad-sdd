# TASK-0008 - Documentacao operacional da fundacao

## Objetivo

Documentar os comandos, convencoes e passos necessarios para executar, testar e validar a fundacao tecnica do projeto.

## Contexto

A documentacao operacional reduz atrito para evoluir o MVP e serve como contrato pratico para as proximas sprints. Ela deve refletir a estrutura real criada na Sprint 0, registrando que todo codigo de aplicacao fica dentro de `codebase/`.

## Requisitos relacionados

- RF: N/A
- RN: N/A
- RNF: RNF-001, RNF-017
- ESPEC: N/A
- ADR: ADR-001, ADR-002

## Escopo tecnico

- Atualizar README principal com visao de execucao local.
- Documentar comandos de backend, frontend, banco, Docker e testes.
- Documentar variaveis de ambiente esperadas usando exemplos sem segredos.
- Documentar a convencao de que todo codigo da aplicacao fica em `codebase/`.
- Registrar convencoes iniciais de estrutura e fluxo de desenvolvimento.
- Atualizar documentos de desenvolvimento quando necessario.

## Criterios de aceite

- Um desenvolvedor consegue subir a stack local seguindo a documentacao.
- Comandos de build, teste e migrations estao documentados.
- Variaveis de ambiente sao descritas sem valores sensiveis.
- Documentacao referencia corretamente a estrutura `docs/desenvolvimento/`.
- Documentacao explicita a separacao entre `codebase/`, `docs/` e `logs/`.

## Arquivos previstos

- `README.md`
- `codebase/.env.example`
- `codebase/`
- `docs/desenvolvimento/`
- `logs/atividades/`

## Testes previstos

- Validacao manual dos comandos documentados.
- Conferencia de que a documentacao nao contem segredos.

## Dependencias

- TASK-0001.
- TASK-0002.
- TASK-0003.
- TASK-0004.
- TASK-0005.
- TASK-0006.
- TASK-0007.

## Status

- [x] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [ ] Concluida

## Notas de implementacao

- Esta task deve ser revisada ao final da Sprint 0 para refletir exatamente o que foi implementado.
