# MAD - Sistema de Gestao de Investimentos

Aplicacao web para investidores pessoa fisica controlarem carteiras de acoes e FIIs.
O sistema substitui planilhas por cadastro estruturado de ativos, operacoes, eventos corporativos, proventos, cotacoes e dashboards.
Todas as implementacoes devem seguir as specs em `docs/` antes de gerar codigo.

## Log de Atividades

- Todas as atividades realizadas no projeto devem ser registradas em `logs/atividades/`.
- Deve existir um arquivo por dia, usando o formato `YYYY-MM-DD.md`.
- Cada registro deve conter, no minimo, horario aproximado, resumo da atividade e arquivos alterados quando houver.
- O log deve ser atualizado ao final de cada atividade relevante.

## Persistencia da Posicao

- A quantidade atual e o Custo Total por ativo e carteira podem ser mantidos em tabela materializada.
- A tabela materializada deve ser atualizada de forma transacional sempre que uma operacao, bonificacao ou evento corporativo for criado, alterado ou excluido.
- Nunca calcular nem persistir preco medio como regra de negocio.
- Eventos corporativos e bonificacoes alteram quantidade, nunca o Custo Total.

## Plano e fluxo de Desenvolvimento

- O plano de desenvolvimento do MVP fica em `docs/desenvolvimento/`.
- Antes de iniciar uma atividade de desenvolvimento, consulte:
  - `docs/desenvolvimento/checkpoint.md`
  - `docs/desenvolvimento/roadmap.md`
  - o arquivo da sprint atual em `docs/desenvolvimento/sprints/`
  - o epico relacionado em `docs/desenvolvimento/epicos/`
- O arquivo `docs/desenvolvimento/checkpoint.md` deve ser tratado como memoria de trabalho volatil.
- Atualize `checkpoint.md` sempre que houver mudanca relevante em:
  - onde paramos;
  - proximo passo imediato;
  - bloqueios;
  - decisoes recentes;
  - arquivos em andamento.
- Nao use `checkpoint.md` como historico permanente. Historico duradouro deve ficar em `logs/atividades/`, arquivos de sprint e tasks.
- Arquivos de task devem ser criados em `docs/desenvolvimento/tasks/` apenas depois da aprovacao das ESPECs relacionadas e antes da criacao da branch da sprint.
- Cada task de sprint deve possuir exatamente um status: `Planejada`, `Em andamento`, `Em revisao` ou `Concluida`.
- Após concluir o desenvolvimento de uma task aguardar revisão.
- A task só deve ser marcada como concluida após aprovação direta.
- Não iniciar nova task se houver outra task em andamento ou em revisão.

## Fluxo de Planejamento e Execucao

- Antes de iniciar qualquer implementacao de sprint, o agente deve validar se as ESPECs relacionadas estao aprovadas.
- Se alguma ESPEC relacionada estiver em status `Rascunho`, `Pendente` ou equivalente, o agente nao deve iniciar codigo da sprint.
- Quando houver ESPEC pendente de aprovacao, o agente deve:
  - informar quais ESPECs bloqueiam a sprint;
  - aguardar aprovacao explicita do usuario;
  - registrar o bloqueio em `docs/desenvolvimento/checkpoint.md`;
  - registrar a atividade em `logs/atividades/`.
- Depois da aprovacao das ESPECs, o agente deve detalhar a sprint em tasks menores antes de implementar codigo.
- As tasks devem ser criadas em `docs/desenvolvimento/tasks/sprint-XX/`, uma por arquivo.
- Cada task deve conter, no minimo:
  - objetivo;
  - contexto;
  - requisitos relacionados;
  - escopo tecnico;
  - criterios de aceite;
  - arquivos previstos;
  - testes previstos;
  - dependencias;
  - status.
- Somente depois de detalhar as tasks da sprint, o agente deve criar uma branch para o desenvolvimento da sprint.
- O nome da branch deve seguir o padrao `codex/sprint-XX-nome-curto`.
- A implementacao da sprint deve ocorrer nessa branch.
- Ao criar ou trocar para a branch da sprint, o agente deve atualizar:
  - `docs/desenvolvimento/checkpoint.md`;
  - o arquivo da sprint em `docs/desenvolvimento/sprints/`;
  - `logs/atividades/`.
- O fluxo obrigatorio e:

```text
ESPEC aprovada
    -> sprint detalhada em tasks
    -> branch da sprint criada
    -> task em andamento
    -> implementacao e testes
    -> task em revisao
    -> aprovacao explicita da task
    -> task concluida
    -> proxima task
```

## Revisao Tecnica e Documentacao de Aprendizados

- Apos uma task ser colocada em `Em revisao`, um agente diferente daquele que implementou a task pode realizar uma revisao tecnica complementar.
- Essa revisao nao substitui a aprovacao explicita do usuario e nao altera o fluxo principal de desenvolvimento.
- O agente revisor deve analisar a task implementada e documentar padroes, boas praticas, conceitos e definicoes relevantes usados na solucao.
- A revisao deve priorizar conhecimento reutilizavel para futuras tasks, sem duplicar documentacao ja existente.
- Quando identificar um padrao ou decisao recorrente, o agente revisor deve sugerir onde documentar:
  - `docs/desenvolvimento/padroes/` para padroes de implementacao e boas praticas do projeto;
  - `docs/arquitetura/adr/` para decisoes arquiteturais relevantes;
  - arquivo da sprint em `docs/desenvolvimento/sprints/` para observacoes especificas da sprint;
  - arquivo da task em `docs/desenvolvimento/tasks/` para notas especificas da task.
- O agente revisor nao deve iniciar nova implementacao nem assumir a task em desenvolvimento.
- Se a revisao encontrar problema funcional, tecnico ou de teste, deve registrar o achado como item de revisao.
- Se a revisao gerar nova documentacao, a atividade deve ser registrada em `logs/atividades/`.
- A documentacao produzida deve ser objetiva, rastreavel e vinculada a task, sprint, ESPEC ou ADR relevante.
- O agente revisor não deve alterar o status da task.

## Documentação de referência

Antes de implementar código, consulte as especificações em `docs/`, especialmente:

- `docs/requisitos/ers.md`
- `docs/requisitos/escopo_mvp.md`
- `docs/arquitetura/documento_arquitetura_MAD.md`
- `docs/arquitetura/modelo_dados.md`
- `docs/integracoes/integracao_api_bolsa_brasil.md`
- `docs/requisitos/especs/`
- `docs/desenvolvimento/`
