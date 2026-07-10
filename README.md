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
