# MAD - Sistema de Gestao de Investimentos

Aplicacao web para investidores pessoa fisica controlarem carteiras de acoes e FIIs.
O sistema substitui planilhas por cadastro estruturado de ativos, operacoes, eventos corporativos, proventos, cotacoes e dashboards.
Todas as implementacoes devem seguir as specs em `docs/` antes de gerar codigo.

## Stack

- Frontend: Angular 21 LTS, Node.js 24 LTS, TypeScript, PrimeNG, Tailwind CSS, Chart.js, ng2-charts
- Backend: Java 25 LTS, Spring Boot 3, Spring Security, JWT, JPA/Hibernate, Flyway
- Banco: PostgreSQL 18
- Cotacoes: Brapi
- Deploy: Vercel frontend, Render backend, Supabase PostgreSQL

## Estrutura Esperada

- `frontend/`: aplicacao Angular
- `backend/`: aplicacao Spring Boot
- `docs/api/openapi.yaml`: contrato REST
- `docs/schema.sql`: modelo de dados
- `docs/business-rules.md`: regras financeiras e validacoes
- `docs/tests/test-scenarios.md`: cenarios tabulares
- `docs/tests/acceptance-tests.feature`: testes de aceitacao
- `docs/domain/domain-model.md`: modelo de dominio
- `docs/domain/state-transitions.md`: transicoes de estado
- `docs/sequences/`: diagramas de sequencia
- `docs/api/error-catalog.md`: catalogo de erros
- `docs/frontend/frontend-architecture.md`: padroes Angular
- `docs/backend/backend-architecture.md`: padroes Spring Boot
- `docs/adr/`: decisoes arquiteturais

## Regras Inegociaveis

- Nunca executar qualquer operacao sem aprovacao explicita do usuario na conversa atual para a acao pretendida. A aprovacao deve estar vinculada a uma acao ou conjunto de acoes claramente descrito. Isso inclui leitura exploratoria, comandos de terminal, criacao ou alteracao de arquivos, instalacao de dependencias, execucao de testes/builds, operacoes Git, abertura de servidores, automacoes e qualquer acao que mude ou inspecione o estado do projeto.
- Nunca calcular ou persistir preco medio como regra de negocio.
- Sempre usar Custo Total conforme RN-01 e RN-02.
- Sempre usar `java.math.BigDecimal` para valores monetarios no backend.
- Proibido usar `double` ou `float` para dinheiro.
- Arredondar valores BRL para 2 casas decimais somente em bordas de exibicao/resposta.
- Posicao atual nao e tabela materializada: calcular sob demanda no `PosicoesService`.
- Eventos corporativos alteram quantidade, nunca Custo Total.
- Proventos sao informados manualmente; o sistema nao calcula valor total recebido.
- Todos os endpoints, exceto `/auth/*`, exigem Bearer JWT.
- Toda excecao de negocio deve mapear para um codigo `ERR-XXX` de `docs/api/error-catalog.md`.

## Segredos

- Nunca hardcodar `BRAPI_TOKEN`, `JWT_SECRET`, senhas ou URLs privadas.
- Java deve ler segredos via variaveis de ambiente ou propriedades externas.
- Angular deve usar arquivos de environment gerados pelo build.
- `.env` nunca deve ser commitado.
- `.env.example` deve conter apenas chaves vazias.
- `BrapiClient` pode cachear cotacoes, nunca segredos.

## Convencoes

- TypeScript: camelCase para variaveis/metodos, PascalCase para tipos/classes.
- Java: PascalCase para classes, camelCase para membros, packages por dominio.
- SQL: snake_case para tabelas, colunas, indices e constraints.
- Angular: Reactive Forms, nunca template-driven forms.
- Backend: Controller -> Service -> Repository, DTOs separados de Entities.

## Comandos Locais

- Ambiente completo: `docker compose up`
- Frontend: `cd frontend && npm test`
- Backend: `cd backend && mvn test`

## Ordem de Consulta Antes de Codar

1. `docs/business-rules.md`
2. `docs/api/openapi.yaml`
3. `docs/domain/domain-model.md`
4. `docs/domain/state-transitions.md`
5. `docs/tests/test-scenarios.md`
6. Arquitetura da camada: `docs/frontend/` ou `docs/backend/`
