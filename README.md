# MAD - Meus Ativos Digitais

O sistema é uma aplicação web voltada para investidores pessoa física que desejam centralizar o controle de sua carteira de renda variável, especificamente Ações e Fundos de Investimento Imobiliário (FIIs), em uma única plataforma.

## Resumo do projeto

O MAD substitui o controle manual em planilhas por um cadastro estruturado de carteiras, ativos, operações, eventos corporativos, bonificações, proventos, cotações e consultas.

No MVP, o sistema deve permitir que o investidor registre compras, vendas e subscrições, acompanhe a posição atual por carteira e ativo, consulte o Custo de Aquisição Total, visualize valor de mercado, P&L, alocação, proventos recebidos e evolução patrimonial. O cadastro de ativos e a atualização diária de cotações serão mantidos por integração externa com dados da bolsa brasileira.

Algumas regras centrais do domínio:

- o sistema controla apenas Ações e FIIs do mercado brasileiro nesta versão;
- o usuário não cadastra ativos manualmente, apenas seleciona ativos do catálogo mantido pela integração;
- operações de compra e subscrição aumentam quantidade e Custo de Aquisição Total;
- vendas reduzem quantidade e Custo de Aquisição Total de forma proporcional;
- bonificações, desdobramentos e grupamentos alteram quantidade, mas nunca alteram o Custo de Aquisição Total;
- preço médio não deve ser calculado nem persistido como regra de negócio.

## Estrutura de pastas atual

```text
.
├── codebase/
├── docs/
│   ├── arquitetura/
│   │   ├── adr/
│   │   ├── documento_arquitetura_MAD.md
│   │   └── modelo_dados.md
│   ├── desenvolvimento/
│   │   ├── epicos/
│   │   ├── sprints/
│   │   ├── tasks/
│   │   ├── README.md
│   │   ├── checkpoint.md
│   │   └── roadmap.md
│   ├── integracoes/
│   │   └── integracao_api_bolsa_brasil.md
│   └── requisitos/
│       ├── especs/
│       ├── ers.md
│       ├── escopo_mvp.md
│       └── espec_template.md
├── logs/
│   └── atividades/
│       └── 2026-07-10.md
├── AGENTS.md
├── Agent
└── README.md
```

### Descrição dos principais diretórios e arquivos

- `codebase/`: diretório reservado para o código da aplicação, incluindo backend, frontend, infraestrutura local e artefatos operacionais.
- `docs/`: documentação funcional, arquitetural e técnica que orienta as implementações.
- `docs/requisitos/`: requisitos do sistema, escopo do MVP e especificações detalhadas por funcionalidade.
- `docs/requisitos/especs/`: especificações funcionais detalhadas do MVP.
- `docs/arquitetura/`: visão arquitetural, stack tecnológica, responsabilidades das camadas e modelo de dados.
- `docs/arquitetura/adr/`: decisões arquiteturais registradas, como estilo arquitetural, banco de dados, OAuth, cotações e autenticação.
- `docs/desenvolvimento/`: planejamento de execução do MVP, com roadmap, checkpoint, épicos, sprints e organização de tasks.
- `docs/integracoes/`: contratos e diretrizes de integração com APIs externas, atualmente focados na API da Bolsa Brasil via Brapi.
- `logs/atividades/`: registros diários das atividades realizadas no projeto.
- `AGENTS.md`: instruções de trabalho para agentes de IA neste repositório.
- `Agent`: arquivo atualmente vazio, mantido no repositório.

## Ambiente local de desenvolvimento

Frontend e backend são executados nativamente no desenvolvimento. O Docker Compose local mantém somente o PostgreSQL 17. Os Dockerfiles das aplicações são destinados aos artefatos de homologação e produção.

O arquivo `codebase/.env.example` concentra os valores de referência para o banco; o arquivo local `codebase/.env` é ignorado pelo Git e deve conter apenas configurações do ambiente do desenvolvedor.

1. Copie `codebase/.env.example` para `codebase/.env`.
2. Preencha `MAD_DB_PASSWORD` em `codebase/.env`.
3. Execute `docker compose up -d postgres` dentro de `codebase/`.
4. Aguarde o PostgreSQL ficar saudável com `docker compose ps`.

Serviços locais e portas padrão:

| Serviço | Endereço local | Configuração da porta |
| --- | --- | --- |
| Frontend | `http://localhost:4200` | Angular CLI (`--port`) |
| Backend | `http://localhost:8080` | Spring Boot (`SERVER_PORT`) |
| PostgreSQL | `localhost:5432` | `MAD_DB_PORT` |

Com o PostgreSQL saudável, execute o backend em `codebase/backend/`:

```bash
mvn spring-boot:run
```

O Spring Boot importa automaticamente `codebase/.env`; variáveis de ambiente do processo continuam tendo precedência. `MAD_DB_HOST`, `MAD_DB_PORT`, `MAD_DB_NAME`, `MAD_DB_USERNAME` e `MAD_DB_PASSWORD` são obrigatórias. A porta pode ser alterada com `SERVER_PORT=<porta> mvn spring-boot:run`. O endpoint técnico padrão fica em `http://localhost:8080/api/v1/system/status`.

Antes de executar o frontend, carregue o `nvm`, selecione o Node.js 24 LTS e, em `codebase/frontend/`, execute:

```bash
nvm use 24
npm start
```

O Angular CLI mantém o hot reload local. A porta pode ser alterada com `npm start -- --port <porta>`.

Comandos do banco, executados dentro de `codebase/`:

```bash
docker compose ps
docker compose logs -f postgres
docker compose stop postgres
docker compose down
```

### Contêineres de homologação e produção

Frontend e backend possuem imagens e ciclos de deploy independentes. Esses Dockerfiles não compõem o ambiente local de desenvolvimento. Os comandos abaixo são executados dentro de `codebase/`:

```bash
docker build -t mad-backend:delivery ./backend
docker build -t mad-frontend:delivery ./frontend
```

O frontend é compilado com Node.js 24 e servido pelo Nginx na porta interna 80. O backend é empacotado com Java 25 e expõe a porta interna 8080. As portas públicas são responsabilidade da plataforma de hospedagem.

### Convenção de migrations

- Migrations ficam em `codebase/backend/src/main/resources/db/migration/`.
- O nome segue `V<versão>__<descrição_em_snake_case>.sql`, por exemplo `V1__initialize_mad_db_schema.sql`.
- Uma migration aplicada não deve ser editada; correções são feitas em uma nova versão.
- O Flyway cria e gerencia o schema `mad_db`; migrations e histórico são executados nesse mesmo schema.
- O Flyway é o único mecanismo permitido para alterar schemas compartilhados.
