# Tasks

Este diretorio agrupa tasks por sprint.

As tasks da Sprint 0 estao detalhadas em `sprint-00/`. Novas tasks devem ser adicionadas quando a respectiva sprint ou backlog forem detalhados e as ESPECs relacionadas estiverem aprovadas.

## Convencao sugerida

- `TASK-0001-nome-curto.md` para Sprint 0.
- `TASK-0101-nome-curto.md` para Sprint 1.
- `TASK-0201-nome-curto.md` para Sprint 2.
- `TASK-BACKLOG-0001-nome-curto.md` para backlog ainda nao alocado.

## Template sugerido

```md
# TASK-XXXX - Nome

## Objetivo

## Contexto

## Requisitos relacionados

- RF:
- RN:
- RNF:
- ESPEC:
- ADR:

## Escopo tecnico

## Criterios de aceite

## Arquivos previstos

## Testes previstos

## Dependencias

## Status

- [x] Planejada
- [ ] Em andamento
- [ ] Em revisao
- [ ] Concluida

## Aprovacao

- Aprovada por:
- Data:
- Evidencia:

## Notas de implementacao
```

## Fluxo de status

`Planejada -> Em andamento -> Em revisao -> Concluida`.

- **Cada task de sprint deve possuir exatamente um status**
- Ao concluir implementacao e testes, a task deve ficar `Em revisao`.
- Somente aprovacao explicita do usuario referente a task permite marca-la como `Concluida`.
- Se houver ajustes na revisao, a task retorna para `Em andamento`.
- Apenas uma task de sprint pode estar `Em andamento` ou `Em revisao` por vez.
