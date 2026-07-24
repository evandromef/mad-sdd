# Catalogo de Testes

Este documento inventaria os testes automatizados existentes no MAD e descreve o
comportamento verificado por cada caso. O catalogo deve ser atualizado sempre que
um teste for criado, alterado ou removido.

## Resumo

| Modulo | Arquivos de teste | Casos de teste | Ferramentas principais |
| --- | ---: | ---: | --- |
| Frontend | 3 | 4 | Angular TestBed, Vitest e Testing Library |
| Backend | 2 | 4 | JUnit 5, Spring Boot Test, AssertJ, MockMvc e Testcontainers |
| **Total** | **5** | **8** | — |

## Frontend

Os testes do frontend ficam em `codebase/frontend/src/` junto aos componentes
testados e usam arquivos com o sufixo `.spec.ts`.

### `src/app/app.spec.ts`

Tipo: teste de componente.

| Caso | Comportamento verificado |
| --- | --- |
| `should create the app` | Renderiza o componente raiz com um provedor de rotas vazio e verifica se a instancia criada e do tipo `App`. |

### `src/app/layout/app-shell/app-shell.spec.ts`

Tipo: teste de componente e interface.

| Caso | Comportamento verificado |
| --- | --- |
| `should render the brand and primary navigation` | Verifica a presenca do link acessivel da marca MAD, seu destino `/`, a navegacao principal e o item Dashboard. |
| `should render the primary operation action` | Verifica a presenca do botao acessivel `Nova operacao`. |

### `src/app/features/dashboard/dashboard-page.spec.ts`

Tipo: teste de componente e interface.

| Caso | Comportamento verificado |
| --- | --- |
| `should render positions as a semantic table` | Renderiza o dashboard com localidade `pt-BR` e verifica a tabela semantica de posicao por ativo, os cabecalhos `Ativo` e `Custo total` e a linha de `PETR4`. |

O caso possui limite de 10 segundos definido no proprio teste.

### Execucao

Pre-requisito: carregar o `nvm` e selecionar o Node.js 24 LTS.

```bash
cd codebase/frontend
source /home/evandro/.nvm/nvm.sh
nvm use 24
npm test
```

Para execucao continua durante o desenvolvimento:

```bash
npm run test:watch
```

## Backend

Os testes do backend ficam em `codebase/backend/src/test/java/`.

### `br/com/mad/system/SystemStatusControllerTest.java`

Tipo: teste da camada web com `@WebMvcTest`, `MockMvc` e a configuracao de
seguranca da aplicacao.

| Caso | Comportamento verificado |
| --- | --- |
| `shouldReturnOkWithUpStatus` | Executa `GET /api/v1/system/status` e verifica o status HTTP 200, o tipo de conteudo compativel com JSON e o campo `status` com valor `UP`. |

### `br/com/mad/database/DatabaseMigrationIntegrationTest.java`

Tipo: teste de integracao com contexto Spring Boot, Flyway e PostgreSQL 17
efemero iniciado pelo Testcontainers.

| Caso | Comportamento verificado |
| --- | --- |
| `shouldConnectUsingExternalDatabaseProperties` | Verifica se a conexao usa host, porta, nome do banco e usuario fornecidos externamente pelo container PostgreSQL. |
| `shouldConfigureFlywayAndHibernateWithApplicationSchema` | Verifica se Flyway gerencia somente o schema `mad_db` e se Flyway e Hibernate usam esse schema como padrao. |
| `shouldApplyInitialMigrationOnlyOnce` | Verifica a aplicacao da migration inicial V1, a criacao do schema `mad_db`, o armazenamento do historico do Flyway nesse schema, a ausencia desse historico em `public` e a idempotencia de uma segunda execucao do Flyway. |

### Execucao

Pre-requisitos: Java 25, Maven e Docker acessivel ao Testcontainers.

```bash
cd codebase/backend
mvn test
```

## Ultima validacao integral

Validacao executada em 23 de julho de 2026:

- frontend: 3 arquivos e 4 casos aprovados, sem falhas;
- backend: 2 classes e 4 casos aprovados, sem falhas, erros ou testes ignorados;
- banco usado nos testes de integracao: PostgreSQL 17.10 via Testcontainers.

Este resultado registra apenas a execucao indicada. O estado corrente deve ser
confirmado novamente depois de qualquer alteracao no codigo ou nos testes.
