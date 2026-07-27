# Checkpoint

Memoria de trabalho volatil do plano de desenvolvimento.

## Onde paramos

- A implementacao anterior da TASK-0003 baseada em PostgreSQL 18 foi descartada conforme orientacao do desenvolvedor.
- TASK-0003 reimplementada com PostgreSQL 17, aprovada diretamente pelo desenvolvedor e concluida.
- TASK-0004 aprovada diretamente pelo desenvolvedor e concluida.
- TASK-0005 aprovada diretamente pelo desenvolvedor e concluida com contrato OpenAPI inicial e Swagger apenas no ambiente de desenvolvimento.
- Achado P2 da TASK-0005 corrigido e revalidado: uma habilitacao externa do Springdoc fora do perfil `development` nao torna a documentacao publica.
- Frontend e backend executam nativamente no desenvolvimento; seus Dockerfiles sao exclusivos para homologacao e producao.
- Ajuste da TASK-0004 revalidado com PostgreSQL saudavel, 4 testes backend, 4 testes frontend e build das duas imagens de entrega.
- PostgreSQL 17 local validado via Docker Compose e Testcontainers; migration V1 aplicada do zero sem erro.
- Banco local, schema da aplicacao e schema de historico do Flyway alinhados em `mad_db`.
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

- Aguardar orientacao para iniciar a TASK-0006.

## Bloqueios

- Nenhum bloqueio tecnico ativo.

## Decisoes recentes

- PostgreSQL 17 substitui o PostgreSQL 18 na stack oficial por oferecer maior estabilidade e compatibilidade com o Flyway adotado pelo projeto.
- Desenvolvimento usa PostgreSQL 17 local via Docker Compose; homologacao e producao usam instancias Aiven dedicadas e separadas.
- Docker para frontend e backend e usado somente nos artefatos de homologacao e producao.
- O arquivo de memoria volatil se chama `checkpoint.md`.
- Tasks da Sprint 0 foram criadas apos aprovacao explicita do usuario.
- Todo codigo da aplicacao deve ficar dentro de `codebase/`; documentacao e logs permanecem em `docs/` e `logs/`.
- O plano usara 9 sprints efetivas mais a Sprint 0.
- Spring Boot 3.5.x sera usado por oferecer suporte ao Java 25 sem migrar para Spring Boot 4.
- O fluxo de revisao e aprovacao se aplica somente ao desenvolvimento das tasks das sprints.
- A TASK-0001 esta aprovada e concluida.
- A TASK-0002 foi aprovada diretamente e esta concluida.
- A TASK-0003 foi aprovada diretamente e esta concluida.
- A TASK-0004 foi aprovada diretamente e esta concluida.
- A TASK-0005 foi aprovada diretamente pelo desenvolvedor e esta concluida.
- Swagger UI e o documento OpenAPI dinamico devem ser habilitados somente pelo perfil Spring `development`.
- A autorizacao publica das rotas OpenAPI e Swagger tambem deve depender do perfil `development`, impedindo exposicao por sobrescrita externa de propriedades em outros ambientes.
- OpenAPI e Swagger UI ficam disponiveis somente quando `development` e o unico perfil ativo; fora dessa condicao, as rotas usam `denyAll()` e handlers especificos retornam HTTP 404 para usuarios anonimos e autenticados, mesmo se Springdoc for habilitado externamente.
- O contrato OpenAPI versionado fica em `docs/api/openapi.yaml`; endpoints de negocio dependem das ESPECs correspondentes aprovadas.
- O frontend executa com Angular CLI e Node.js 24 no desenvolvimento; sua imagem de entrega usa Nginx.
- O backend executa com Maven e Java 25 no desenvolvimento; sua imagem de entrega usa JRE Java 25.
- Migrations seguem `V<versao>__<descricao_em_snake_case>.sql` e nao podem ser editadas depois de aplicadas.
- Configuracoes de conexao do banco nao possuem valores de fallback no runtime; o banco e o schema `mad_db` permanecem uma decisao estrutural fixa.
- A URL JDBC e composta por `MAD_DB_HOST`, `MAD_DB_PORT` e `MAD_DB_NAME`, evitando duplicacao da porta em uma URL completa.
- O Spring Boot importa automaticamente `codebase/.env` no desenvolvimento local.
- Flyway e Hibernate usam `mad_db` como schema padrao; o Flyway cria o schema e mantem nele o historico de migrations.
- O teste de integracao publica os valores reais do Testcontainers como propriedades `MAD_DB_*`, exercitando a composicao da conexao definida em `application.yml`.
- Testes backend devem preferir cenarios pequenos, variaveis descritivas imediatamente acima do assert correspondente, helpers focados e descricoes concisas em `as()`.
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
- `codebase/backend/`
- `codebase/docker-compose.yml`

## Ultima atualizacao

- Data/hora: 2026-07-27 16:25 America/Sao_Paulo
- Responsavel: Codex
