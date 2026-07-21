# MAD - Sistema de Gestao de Investimentos

Aplicacao web para investidores pessoa fisica controlarem carteiras de acoes e FIIs.
O sistema substitui planilhas por cadastro estruturado de ativos, operacoes, eventos corporativos, proventos, cotacoes e dashboards.
Todas as implementacoes devem seguir as specs em `docs/` antes de gerar codigo.

## Referencias Obrigatorias e Regras Inviolaveis

- O MAD e uma aplicacao web para controle de carteiras de Acoes e FIIs por investidores pessoa fisica.
- O objetivo do projeto e substituir planilhas por registros estruturados, rastreaveis e consultaveis.
- O MVP cobre cadastro de ativos via catalogo, carteiras, operacoes, eventos corporativos, proventos, cotacoes e dashboards.

**Regra inegociável para o Codex:** nenhuma alteração de código ou geração de novo arquivo deve ser feita sem autorização explícita do desenvolvedor. O Codex deve sempre apresentar o que pretende fazer e aguardar aprovação antes de executar.

### Stack Oficial

- **Frontend:** Angular 21 LTS, Node.js 24 LTS e TypeScript.
- **Backend:** Spring Boot 3 e Java 25 LTS.
- **Banco de dados:** PostgreSQL 17.
- **Dados de mercado:** Brapi.

### Estrutura de Pastas

- `codebase/frontend/`: aplicacao Angular.
- `codebase/backend/`: API Spring Boot.
- `docs/requisitos/`: ERS, escopo do MVP, template e ESPECs.
- `docs/arquitetura/`: documento de arquitetura, modelo de dados e ADRs.
- `docs/integracoes/`: contratos e diretrizes de integracoes externas.
- `docs/desenvolvimento/`: roadmap, checkpoint, epicos, sprints, tasks e padroes.
- `logs/atividades/`: log diario das atividades do projeto.

### Convencoes de Nomenclatura

- TypeScript: `camelCase` para variaveis, propriedades, funcoes e metodos; `PascalCase` para classes, componentes, tipos e interfaces exportadas.
- Java: `PascalCase` para classes, records e enums; `camelCase` para metodos, parametros, variaveis e atributos.
- SQL e banco de dados: `snake_case` para tabelas, colunas, indices e constraints.
- Arquivos devem seguir a convencao do framework do modulo alterado.

### Regras Funcionais Inegociaveis

- Nunca calcular, persistir ou exibir preco medio como regra de negocio.
- Sempre usar quantidade atual e Custo de Aquisicao Total para posicao.
- Operacoes de venda reduzem Custo de Aquisicao Total proporcionalmente a quantidade vendida.
- Bonificacoes, desdobramentos e grupamentos alteram quantidade, nunca Custo de Aquisicao Total.
- Valores monetarios em BRL devem preservar precisao decimal no processamento e ser arredondados para 2 casas apenas na resposta da API e na exibicao.
- A Brapi deve ser consumida apenas pelo backend e somente conforme o documento de integracao.
- A rotina diaria de cotacoes deve consultar apenas ativos referenciados em carteiras e um ticker por chamada, respeitando o plano gratuito da Brapi.

### Ambiente de Execução do Agente e Comandos Padrao

- O agente executa comandos no Ubuntu sobre WSL 2.
- Os comandos do projeto devem usar ferramentas Linux instaladas dentro do WSL, nunca executáveis herdados do Windows por meio de caminhos em `/mnt/c/`.
- O Node.js Linux é gerenciado pelo `nvm`. Antes de executar comandos de frontend, o agente deve carregar o `nvm` e selecionar a versão 24 LTS.
- Execucao local integrada: `docker-compose up`, quando o compose de desenvolvimento estiver configurado.
- Testes frontend: executar em `codebase/frontend/` com `npm test`.
- Testes backend: executar em `codebase/backend/` com `mvn test`.
- Se o comando padrao ainda nao estiver disponivel ou falhar por configuracao pendente, registrar claramente o motivo no final da atividade.

### Contratos e Modelos

- Contratos OpenAPI da API MAD: devem ficar em `docs/api/` quando a TASK-0005 for implementada. Enquanto isso, consultar a task `docs/desenvolvimento/tasks/sprint-00/TASK-0005-openapi-contrato-inicial-api.md`.
- Modelo de dados atual: `docs/arquitetura/modelo_dados.md`.
- Modelo de dominio: ainda nao ha documento dedicado; Gerar modelo de dominio antes de gerar Services ou Entities, consultar ERS, ESPECs relacionadas e modelo de dados.
- Transicoes de estado: ainda nao ha documento dedicado; antes de gerar logica condicional baseada em status ou estado, consultar ESPECs, modelo de dados e tasks/sprints relacionadas.
- Diagramas de sequencia: ainda nao ha pasta dedicada; antes de gerar servicos com multiplas camadas ou chamadas entre servicos, consultar ESPECs e arquitetura.
- Catalogo de erros: ainda nao ha documento dedicado; nao inventar codigos ou mensagens globais sem antes propor/documentar o catalogo.
- Testes de aceitacao: quando forem criados em Gherkin, devem ser derivados das ESPECs e usados como contrato executavel do comportamento esperado.
- Arquitetura frontend/backend: enquanto nao houver documentos dedicados em `docs/frontend/` e `docs/backend/`, consultar `docs/arquitetura/documento_arquitetura_MAD.md` e `docs/desenvolvimento/padroes/`.
- Documentos ainda inexistentes devem ser criados antes da primeira implementacao que dependa deles, conforme a necessidade da sprint/task.
- Nao criar documentacao especulativa sem uso imediato, mas tambem nao implementar codigo que dependa de modelo de dominio, transicoes de estado, diagramas de sequencia, catalogo de erros, contratos de API ou arquitetura especifica sem antes documentar a referencia minima correspondente.
- Sempre que um desses documentos previstos for criado, o agente deve sugerir a atualizacao deste `AGENTS.md` para substituir a referencia pendente pelo caminho definitivo do documento gerado.

### Seguranca de Segredos

- `BRAPI_TOKEN`, `JWT_SECRET`, credenciais de banco e qualquer outro segredo nunca podem aparecer como valores literais no codigo, documentacao operacional versionada, logs, testes ou exemplos.
- No backend Java, segredos devem vir de propriedades externas, variaveis de ambiente ou mecanismo padrao do Spring, nunca de string hardcoded.
- No frontend Angular, usar arquivos de environment apenas para configuracoes publicas, como URLs. Segredos reais nunca devem ir para o bundle.
- E proibido gerar codigo semelhante a:

  ```java
  String token = "meu-token-brapi-123";
  String secret = "minha-chave-jwt";
  ```

- Clientes de integracao nunca devem cachear valores de segredo.
- `BrapiClient` ou equivalente deve ser acessado por servicos/adaptadores do projeto, com deduplicacao de tickers, respeito a rate limit, timeout, retry controlado e consulta previa ao estado ja persistido quando aplicavel.

## Log de Atividades

- Todas as atividades realizadas no projeto devem ser registradas em `logs/atividades/`.
- Deve existir um arquivo por dia, usando o formato `YYYY-MM-DD.md`.
- Cada registro deve conter, no minimo, horario aproximado, resumo da atividade e arquivos alterados quando houver.
- Os registros do log diario devem ser mantidos em ordem decrescente, com a atividade mais recente no topo do arquivo.
- O log deve ser atualizado ao final de cada atividade relevante.

## Persistencia da Posicao

- A quantidade atual e o Custo Total por ativo e carteira podem ser mantidos em tabela materializada.
- A tabela materializada deve ser atualizada de forma transacional sempre que uma operacao, bonificacao ou evento corporativo for criado, alterado ou excluido.
- Nunca calcular nem persistir preco medio como regra de negocio.
- Eventos corporativos e bonificacoes alteram quantidade, nunca o Custo Total.

## Padrao de Engenharia do Agente Desenvolvedor

### Padroes de Codigo

- Seguir as convencoes do framework e da linguagem usados no modulo alterado.
- Manter funcoes e metodos pequenos, preferencialmente com uma unica responsabilidade.
- Evitar arquivos grandes ou com muitas responsabilidades. Separar por dominio, camada ou caso de uso quando necessario.
- Usar nomes especificos e descritivos para classes, metodos, variaveis e arquivos.
- Evitar nomes genericos como `data`, `handler`, `manager`, `utils` ou equivalentes quando houver nome de dominio mais claro.
- Evitar duplicacao de codigo. Extrair logica compartilhada para modulo, servico ou funcao reutilizavel.
- Preferir retornos antecipados quando isso reduzir aninhamento e melhorar legibilidade.
- Mensagens de erro devem conter o valor invalido e o formato ou regra esperada quando aplicavel.
- Nao introduzir comentarios obvios. Comentarios devem explicar contexto, decisao ou motivo.
- Nao remover comentarios existentes sem verificar se documentam uma decisao, restricao ou contexto relevante.
- Funcoes, classes ou metodos publicos relevantes devem ter documentacao curta explicando intencao e exemplo de uso quando isso ajudar o consumidor da API.

### Padroes de Testes

- Toda nova regra de negocio deve ter teste automatizado.
- Correcoes de bugs devem incluir teste de regressao quando viavel.
- Testes devem ser rapidos, independentes, repetiveis e auto-verificaveis.
- I/O externo, APIs, banco de dados e filesystem devem ser isolados por mocks, fakes ou infraestrutura de teste apropriada.
- Preferir fakes nomeados e reutilizaveis a stubs inline complexos.
- Antes de finalizar uma alteracao, executar o comando padrao de testes do projeto ou registrar claramente por que nao foi executado.

### Dependencias e Estruturas

- Injetar dependencias por construtor, parametro ou mecanismo padrao do framework.
- Evitar dependencias globais implicitas.
- Bibliotecas externas devem ser acessadas por interfaces ou adaptadores do proprio projeto quando influenciarem regra de negocio, I/O externo ou integracoes.
- Seguir a estrutura convencional do framework e os limites de responsabilidade ja existentes no projeto.
- Preferir modulos pequenos e focados a arquivos genericos ou concentradores.

### Formatacao

- Usar o formatador padrao da linguagem ou framework do modulo alterado.
- Nao discutir estilo quando houver formatador automatico configurado.
- Nao misturar alteracoes de formatacao ampla com mudancas funcionais sem necessidade.

## Plano e fluxo de Desenvolvimento

- O plano de desenvolvimento do MVP fica em `docs/desenvolvimento/`.
- O agente desenvolvedor deve seguir o padrao de engenharia do projeto em toda implementacao.
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
- A atividade de revisão deve ser registrada em `logs/atividades/`.
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
