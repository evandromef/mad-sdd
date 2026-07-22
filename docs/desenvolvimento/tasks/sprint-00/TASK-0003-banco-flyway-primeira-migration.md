# TASK-0003 - Banco local, Flyway e primeira migration

## Objetivo

Configurar PostgreSQL 17 local e Flyway para permitir evolucao versionada do schema desde o inicio do projeto.

## Contexto

O MAD depende de persistencia relacional, transacoes ACID, UUIDs, tipos numericos precisos e migracoes reprodutiveis. A Sprint 0 deve garantir que o banco rode do zero e que a API em `codebase/backend/` esteja preparada para evoluir o modelo de dados nas proximas sprints.

## Requisitos relacionados

- RF: N/A
- RN: RN-001, RN-002, RN-003
- RNF: RNF-001, RNF-012, RNF-013, RNF-017
- ESPEC: N/A
- ADR: ADR-002

## Escopo tecnico

- Configurar conexao local da API com PostgreSQL 17.
- Configurar Flyway no backend em `codebase/backend/`.
- Criar primeira migration tecnica minima.
- Definir convencao de migrations.
- Preparar configuracao para Testcontainers nos testes de integracao.

## Criterios de aceite

- PostgreSQL 17 local sobe em ambiente de desenvolvimento.
- Flyway executa as migrations do zero sem erro.
- API conecta no banco local usando variaveis de ambiente ou perfil local.
- Migration inicial nao implementa regra funcional fora do escopo da Sprint 0.

## Arquivos previstos

- `codebase/backend/src/main/resources/application*.yml`
- `codebase/backend/src/main/resources/db/migration/`
- `codebase/docker-compose.yml`
- `README.md`

## Testes previstos

- Teste de integracao simples validando contexto com PostgreSQL 17 via Testcontainers.
- Execucao local de migrations em banco limpo.

## Dependencias

- TASK-0001.
- Docker disponivel no ambiente local.

## Status

- [ ] Planejada
- [ ] Em andamento
- [x] Em revisao
- [ ] Concluida

## Notas de implementacao

- Usar `NUMERIC` e UUID conforme ADR-002 quando tabelas de negocio forem criadas em sprints futuras.
- Nao criar preco medio em nenhuma estrutura persistida.
- PostgreSQL 17 local configurado como servico isolado no Docker Compose; backend e frontend serao adicionados na TASK-0004.
- O Flyway cria e gerencia o schema tecnico `mad_db`; a migration `V1__initialize_mad_db_schema.sql` valida o schema ativo sem criar tabelas ou regras funcionais.
- O teste de integracao usa PostgreSQL 17 via Testcontainers e valida a versao aplicada e a existencia do schema.
- Flyway executou a migration em PostgreSQL 17.10 sem aviso de incompatibilidade de versao.
- URL, usuario, senha, nome do banco e porta nao possuem fallback no runtime; valores locais de referencia ficam apenas em `.env.example`.
- O schema `mad_db` permanece fixo por ser parte estrutural do modelo versionado por migrations.
- A URL JDBC e montada a partir de host, porta e nome do banco para impedir divergencia entre `MAD_DB_PORT` e uma URL completa duplicada.
- A porta interna `5432` permanece fixa no Compose por ser o contrato da imagem oficial do PostgreSQL; apenas a porta publicada e configuravel.
- O banco local e o schema da aplicacao usam o nome `mad_db`.
- Flyway, Hibernate e historico de migrations usam explicitamente o schema `mad_db`, evitando variacao causada pelo `search_path` do PostgreSQL.
- O Spring Boot importa automaticamente `codebase/.env` no desenvolvimento local; variaveis do processo mantem precedencia.

## Revisao tecnica complementar

- Data: 2026-07-21
- Revisor: Codex
- Escopo revisado: documentacao vigente, criterios de aceite, configuracao Spring, Docker Compose, Flyway, migration V1 e testes com PostgreSQL 17.
- Status da task: mantido como `Em revisao`, aguardando aprovacao direta do desenvolvedor.

### Resultado

- A implementacao esta alinhada ao PostgreSQL 17 definido na stack, na arquitetura e na ADR-002.
- A configuracao exige externamente host, porta, nome do banco, usuario e senha, sem valores silenciosos de fallback no runtime.
- A chave literal `hibernate.default_schema` possui teste de regressao que confirma o binding para o schema estrutural `mad_db`.
- A URL JDBC e composta sem duplicar a porta em uma variavel de URL completa, e a porta interna do contêiner permanece fixa em `5432`.
- O Flyway cria o schema tecnico `mad_db`, e a migration V1 valida que ele e o schema ativo sem antecipar tabelas ou regras funcionais.
- O schema padrao e o historico do Flyway estao alinhados em `mad_db`, com teste de segunda execucao sem migration pendente.
- `mvn test` foi executado em 2026-07-21 com Java 25 e PostgreSQL 17.10 via Testcontainers: 2 testes aprovados, sem falhas ou erros.
- O backend foi iniciado duas vezes consecutivas com `mvn spring-boot:run`, sem importacao manual do `.env`; na segunda inicializacao, o Flyway reconheceu a versao 1 no schema `mad_db` e nao tentou reaplicar a migration.
- O PostgreSQL local foi recriado com o banco `mad_db`; a inspecao final confirmou o schema e o historico em `mad_db.flyway_schema_history`.
- O Docker Compose foi validado com todas as variaveis obrigatorias, e `codebase/.env` foi confirmado como ignorado pelo Git.
- Os dois achados documentais da revisao foram corrigidos no README e no indice de ADRs do documento de arquitetura.

### Item de revisao corrigido

#### [P1] Alinhar o schema padrao do Flyway ao schema da aplicacao

- Evidencia original: `spring.flyway.default-schema` estava definido como `public`, enquanto `hibernate.default_schema` estava definido como `mad_db`.
- Impacto: a V1 funciona por qualificar explicitamente a criacao de `mad_db`, mas uma migration futura com DDL nao qualificado, como `CREATE TABLE usuario (...)`, criara o objeto em `public`; o Hibernate procurara o mesmo objeto em `mad_db`.
- Correcao recomendada: usar `mad_db` como schema padrao do Flyway e do Hibernate, mantendo banco local, schema da aplicacao e historico do Flyway alinhados. Configurar explicitamente `spring.flyway.default-schema=mad_db`, `spring.flyway.schemas=mad_db` e a criacao controlada do schema pelo Flyway.
- Ajuste esperado na V1: como o Flyway pode criar o schema antes da migration, substituir `CREATE SCHEMA mad_db` por uma inicializacao tecnica compativel com o schema ja criado, sem introduzir tabelas ou regras funcionais.
- Validacao esperada: confirmar `mad_db.flyway_schema_history`, ausencia de historico em `public`, segunda execucao com zero migrations aplicadas, binding do Hibernate para `mad_db` e criacao de objetos nao qualificados no schema `mad_db`.
- Documentacao esperada: atualizar README, notas da task e checkpoint para remover a separacao entre historico em `public` e schema da aplicacao em `mad_db`.
- Correcao aplicada: Flyway, Hibernate e historico foram alinhados em `mad_db`; a V1 valida o schema ativo sem criar objetos funcionais.
- Validacao adicionada: o teste confirma `mad_db.flyway_schema_history`, ausencia de historico em `public`, configuracao dos schemas e segunda execucao com zero migrations aplicadas.
- Validacao local concluida: banco recriado, duas inicializacoes consecutivas aprovadas e catalogo confirmado com historico somente em `mad_db`.
- Revalidacao tecnica: em 2026-07-21, a revisao confirmou o schema ativo `mad_db`, o historico em `mad_db.flyway_schema_history`, a ausencia do historico em `public` e zero migrations aplicadas na segunda execucao.
- Testes da revalidacao: `mvn test` executou 2 testes com sucesso; uma execucao adicional de `mvn clean test` foi impedida por restricoes do ambiente isolado ao Docker e ao mecanismo de anexacao do Mockito, sem indicar defeito da implementacao.
- Status do achado: corrigido e aprovado na revisao tecnica; nenhum item de revisao permanece aberto.
- Status da task: mantido como `Em revisao`, aguardando aprovacao direta do desenvolvedor.
