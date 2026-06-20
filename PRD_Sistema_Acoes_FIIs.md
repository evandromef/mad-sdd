# PRD — Sistema de Gestão de Investimentos em Ações e FIIs

**Versão:** 1.0  
**Data:** Junho de 2026  
**Autor:** [Nome do Responsável]  
**Status:** Rascunho

---

## 1. Visão Geral do Produto

### 1.1 Resumo

O sistema é uma aplicação voltada para investidores pessoa física que desejam centralizar o controle de sua carteira de renda variável — especificamente Ações e Fundos de Investimento Imobiliário (FIIs) — em uma única plataforma. O objetivo é substituir planilhas manuais por uma ferramenta estruturada que ofereça cadastro de ativos, acompanhamento de patrimônio, controle de proventos e visualização analítica da carteira.

### 1.2 Problema

Investidores pessoa física geralmente gerenciam suas carteiras por meio de planilhas ou ferramentas genéricas, o que gera:

- Dificuldade em consolidar múltiplas operações (compra, venda, desdobramento, grupamento)
- Ausência de histórico centralizado de proventos (dividendos, JCP, rendimentos de FII)
- Falta de visibilidade sobre custo total, rentabilidade e posição atual
- Processo manual e propenso a erros para conciliação com notas de corretagem

### 1.3 Solução

Uma aplicação web responsiva que permita ao usuário:

- Cadastrar e gerenciar sua carteira de Ações e FIIs
- Registrar operações de compra, venda e eventos corporativos
- Acompanhar o patrimônio consolidado em tempo real (ou com cotação atualizada)
- Registrar e visualizar o histórico de proventos recebidos
- Acessar dashboards e relatórios analíticos da sua carteira

---

## 2. Objetivos e Métricas de Sucesso

### 2.1 Objetivos do Produto

| # | Objetivo | Descrição |
|---|----------|-----------|
| O1 | Centralização | Consolidar todas as posições em ações e FIIs em um único lugar |
| O2 | Precisão | Calcular automaticamente custo total, P&L e rentabilidade por ativo |
| O3 | Rastreabilidade | Manter histórico completo de operações e proventos |
| O4 | Usabilidade | Interface intuitiva, sem necessidade de conhecimento técnico avançado |
| O5 | Confiabilidade | Dados íntegros mesmo após eventos corporativos (splits, bonificações) |

### 2.2 Métricas de Sucesso (KPIs)

- Tempo médio para registrar uma operação: < 1 minuto
- Precisão do cálculo de custo total: 100% (validado contra cálculo manual)
- Cobertura de eventos corporativos suportados: ≥ 5 tipos no MVP
- Taxa de retorno do usuário na primeira semana: ≥ 60%

---

## 3. Público-Alvo

### 3.1 Persona Principal — "O Investidor Autônomo"

- **Perfil:** Pessoa física, 25–50 anos, que investe diretamente em bolsa pela própria conta
- **Experiência:** Intermediária — conhece os conceitos de renda variável, mas não tem formação técnica em TI
- **Ferramentas atuais:** Planilhas Excel/Google Sheets, aplicativos da corretora
- **Dores:** Dificuldade em controlar o custo total das posições, perder o histórico de dividendos, não saber a rentabilidade real da carteira
- **Motivação:** Ter controle financeiro real e tomar decisões de investimento mais informadas

---

## 4. Escopo do MVP

### 4.1 Funcionalidades Incluídas no MVP

#### F00 — Gestão de Carteiras

- O usuário pode criar, editar e excluir múltiplas carteiras (ex: "Carteira Principal", "Carteira Aposentadoria")
- Cada operação, posição e provento é vinculado a uma carteira específica
- Visão consolidada opcional somando todas as carteiras do usuário
- Seletor de carteira disponível em todas as telas principais

#### F01 — Cadastro de Ativos

- Cadastro manual de Ações (ticker, nome da empresa, setor, segmento)
- Cadastro manual de FIIs (ticker, nome do fundo, tipo de fundo, segmento)
- Busca de ativos por ticker
- Edição e desativação de ativos cadastrados

#### F02 — Registro de Operações

- Lançamento de operações de **Compra** e **Venda**
- Campos: data, ativo, quantidade, valor total da operação (já incluindo taxas/corretagem), preço unitário (opcional, apenas informativo), taxas (opcional, apenas informativo)
- Histórico completo de operações por ativo
- Edição e exclusão de operações (com recalculo automático do Custo Total)

#### F03 — Eventos Corporativos

- Registro manual de **Splits e Grupamentos**: ativo, data, proporção, nova quantidade, descrição (opcional)
- Registro manual de **Bonificações**: ativo, data, quantidade recebida, descrição (opcional)
- Em ambos os casos, apenas a quantidade do ativo é ajustada; o Custo Total permanece inalterado
- Histórico de eventos corporativos por ativo, separado do histórico de operações
- Edição e exclusão de eventos (com recalculo automático da quantidade)

#### F04 — Carteira e Posição Atual

- Visão consolidada da carteira com posição atual por ativo
- Cálculo automático do **Custo Total** de cada ativo
- Exibição de quantidade atual, Custo Total, valor de mercado e P&L (Lucro/Prejuízo)
- Separação de carteira em Ações e FIIs
- Indicador de percentual de alocação por ativo e por classe
- Tela de detalhe do ativo, exibindo: quantidade atual, Custo Total, valor de mercado atual, P&L não realizado, histórico de operações (compra/venda), histórico de eventos corporativos (splits, grupamentos, bonificações) e histórico de proventos/rendimentos recebidos
- A tela de detalhe do ativo deve permitir o acesso direto a todas as funções relacionadas ao ativo: registrar operação de compra/venda, registrar evento corporativo (split, grupamento, bonificação) e registrar provento
- Área de comentários/notas livres por ativo, onde o usuário pode inserir, editar e visualizar anotações pessoais (ex: teses de investimento, observações, lembretes)

#### F05 — Controle de Proventos

- Registro manual de proventos recebidos por ativo (data, tipo, valor por cota/ação)
- Tipos de proventos: Dividendo, Juros sobre Capital Próprio (JCP), Rendimento (FII), Amortização
- Histórico de proventos por ativo e consolidado
- Cálculo de total de proventos recebidos no período (mensal, trimestral, anual)

#### F06 — Dashboard Analítico

- Resumo do patrimônio total (Ações + FIIs)
- Distribuição da carteira em gráfico (pizza/donut) por ativo e por classe
- Evolução do patrimônio ao longo do tempo (gráfico de linha)
- Resumo de proventos recebidos por mês (gráfico de barras)
- Indicadores: rentabilidade total e maior posição

#### F07 — Integração com API de Cotações

- Integração com API externa de cotações (ex: Brapi, B3, Yahoo Finance) para obter o preço atual dos ativos
- Atualização automática diária (ou sob demanda) das cotações de todos os ativos da carteira
- Cálculo automático do lucro/prejuízo não realizado (P&L) com base na cotação do dia
- Armazenamento da cotação diária atual (sobrescrita a cada atualização) e de um snapshot mensal histórico por ativo, usado para gerar a evolução do patrimônio
- Tratamento de falha na API: manter última cotação válida e exibir indicador de "cotação desatualizada"

### 4.2 Funcionalidades Fora do Escopo do MVP (Futuras)

- Importação automática de notas de corretagem (PDF)
- Cálculo de IR (Imposto de Renda sobre operações)
- Suporte a outros ativos (BDRs, ETFs, Renda Fixa, Cripto)
- Alertas e notificações de eventos (pagamento de proventos, datas ex)
- Exportação para declaração de IR na Receita Federal

---

## 5. Requisitos Funcionais Detalhados

### 5.1 Módulo de Carteiras

| ID | Requisito |
|----|-----------|
| RF-C01 | O sistema deve permitir que o usuário crie múltiplas carteiras com nome e descrição |
| RF-C02 | O sistema deve permitir editar e excluir carteiras (exclusão exige confirmação se houver operações vinculadas) |
| RF-C03 | Toda operação, posição e provento deve estar vinculado a uma carteira específica |
| RF-C04 | O sistema deve permitir alternar entre carteiras por meio de um seletor disponível nas telas principais |
| RF-C05 | O sistema deve exibir uma visão consolidada somando o patrimônio de todas as carteiras do usuário |
| RF-C06 | O sistema deve impedir a exclusão da última carteira do usuário (deve haver ao menos uma) |

### 5.2 Módulo de Ações

| ID | Requisito |
|----|-----------|
| RF-A01 | O sistema deve permitir cadastrar uma ação com: ticker, nome, setor, segmento B3 |
| RF-A02 | O sistema deve impedir cadastro duplicado de ticker |
| RF-A02b | O sistema deve validar o ticker informado contra a API externa de cotações, confirmando que se trata de uma ação válida e ativa na B3 antes de permitir o cadastro |
| RF-A03 | O sistema deve permitir registrar operação de compra com: data, quantidade e valor total da operação (já incluindo taxas/corretagem). Os campos de preço unitário e taxas são opcionais e têm caráter apenas informativo |
| RF-A04 | O sistema deve calcular o Custo Total do ativo somando o valor total informado em cada compra |
| RF-A05 | O sistema deve recalcular o Custo Total ao registrar uma venda, conforme a fórmula: Novo Custo Total = Custo Total Atual − ((Custo Total Atual ÷ Qtd Atual) × Qtd Vendida) |
| RF-A06 | O sistema deve exibir o P&L não realizado: (Quantidade Atual × Cotação Atual) − Custo Total |
| RF-A07 | O sistema deve exibir o Custo Total atual de cada ativo na visão da carteira |

### 5.3 Módulo de Eventos Corporativos

| ID | Requisito |
|----|-----------|
| RF-E01 | O sistema deve permitir registrar manualmente eventos de Split/Grupamento com: ativo, data, proporção, nova quantidade, descrição (opcional) |
| RF-E02 | O sistema deve permitir registrar manualmente eventos de Bonificação com: ativo, data, quantidade recebida, descrição (opcional) |
| RF-E03 | Ao registrar um evento de Split/Grupamento, o sistema deve ajustar apenas a quantidade do ativo, mantendo o Custo Total inalterado |
| RF-E04 | Ao registrar uma Bonificação, o sistema deve incrementar a quantidade do ativo pela quantidade recebida, mantendo o Custo Total inalterado |
| RF-E05 | O sistema deve exibir o histórico de eventos corporativos por ativo, separado do histórico de operações de compra/venda |
| RF-E06 | O sistema deve permitir editar e excluir eventos corporativos, recalculando automaticamente a quantidade do ativo |

### 5.4 Módulo de FIIs

| ID | Requisito |
|----|-----------|
| RF-F01 | O sistema deve permitir cadastrar um FII com: ticker, nome, tipo (Tijolo, Papel, Híbrido, FOF), segmento |
| RF-F01b | O sistema deve validar o ticker informado contra a API externa de cotações, confirmando que se trata de um FII válido e ativo antes de permitir o cadastro |
| RF-F02 | O sistema deve permitir registrar compra e venda de cotas com os mesmos campos das ações |
| RF-F03 | O sistema deve calcular o Custo Total de cada FII, seguindo as mesmas regras do módulo de Ações |
| RF-F04 | O sistema deve aplicar o módulo de Eventos Corporativos (RF-E01 a RF-E06) também a FIIs, para registro de amortizações de cotas, desdobramentos e grupamentos |

### 5.5 Módulo de Proventos

| ID | Requisito |
|----|-----------|
| RF-P01 | O sistema deve permitir registrar proventos com: ativo, tipo, data, valor total recebido, valor por ação/cota (campo opcional) |
| RF-P02 | O sistema não deve calcular o valor total recebido; o valor deve ser informado manualmente pelo usuário |
| RF-P03 | O sistema deve exibir histórico de proventos por ativo e consolidado |
| RF-P04 | O sistema deve permitir filtrar proventos por período |
| RF-P05 | O sistema deve somar o total recebido em proventos no mês, trimestre e ano |

### 5.6 Módulo de Cotações (Integração API)

| ID | Requisito |
|----|-----------|
| RF-Q01 | O sistema deve consultar uma API externa de cotações para obter o preço atual de cada ativo cadastrado |
| RF-Q02 | O sistema deve atualizar as cotações automaticamente em rotina diária (ex: após o fechamento do mercado) |
| RF-Q03 | O sistema deve permitir atualização manual de cotações sob demanda ("Atualizar agora") |
| RF-Q04 | O sistema deve manter apenas a cotação diária mais recente de cada ativo (sobrescrita a cada atualização) e armazenar um snapshot mensal histórico para alimentar o gráfico de evolução do patrimônio |
| RF-Q05 | O sistema deve exibir um indicador visual quando a cotação exibida estiver desatualizada (falha na API) |
| RF-Q06 | O sistema deve recalcular o P&L não realizado de todos os ativos sempre que novas cotações forem obtidas |

### 5.7 Módulo de Dashboard

| ID | Requisito |
|----|-----------|
| RF-D01 | O sistema deve exibir o patrimônio total da carteira na tela principal |
| RF-D02 | O sistema deve exibir a alocação por ativo em gráfico percentual |
| RF-D03 | O sistema deve exibir a alocação por classe (Ações vs FIIs) |
| RF-D04 | O sistema deve exibir um gráfico de evolução do patrimônio com base nos snapshots mensais históricos de cotação |
| RF-D05 | O sistema deve exibir um resumo mensal de proventos recebidos |
| RF-D06 | O sistema deve permitir alternar a visualização do dashboard entre carteira individual e visão consolidada |

### 5.8 Módulo de Detalhe do Ativo

| ID | Requisito |
|----|-----------|
| RF-DET01 | O sistema deve exibir uma tela de detalhe por ativo com: quantidade atual, Custo Total, valor de mercado atual e P&L não realizado |
| RF-DET02 | A tela de detalhe deve exibir o histórico de operações (compra/venda) do ativo |
| RF-DET03 | A tela de detalhe deve exibir o histórico de eventos corporativos (splits, grupamentos e bonificações) do ativo |
| RF-DET04 | A tela de detalhe deve exibir o histórico de proventos/rendimentos recebidos do ativo |
| RF-DET05 | A tela de detalhe deve exibir uma área de comentários/notas livres, permitindo ao usuário inserir, editar, excluir e visualizar anotações pessoais sobre o ativo |
| RF-DET06 | A tela de detalhe deve disponibilizar atalhos para todas as funções relacionadas ao ativo: registrar operação de compra/venda, registrar evento corporativo e registrar provento |

---

## 6. Requisitos Não Funcionais

| ID | Categoria | Requisito |
|----|-----------|-----------|
| RNF-01 | Desempenho | Carregamento do dashboard em < 2 segundos para carteiras com até 50 ativos |
| RNF-02 | Usabilidade | Interface responsiva para mobile e desktop |
| RNF-03 | Segurança | Dados do usuário armazenados com autenticação e acesso restrito por conta |
| RNF-04 | Confiabilidade | Cálculos de custo total e P&L com precisão de 2 casas decimais (moeda BRL) |
| RNF-05 | Escalabilidade | Suportar carteiras com até 200 ativos distintos sem degradação perceptível |
| RNF-06 | Manutenibilidade | Código organizado em módulos independentes por entidade de domínio |
| RNF-07 | Acessibilidade | Suporte a leitores de tela para componentes principais (WCAG 2.1 nível AA) |

---

## 7. Regras de Negócio

### 7.1 Validação de Ticker

- Todo cadastro de novo ativo (ação ou FII) exige validação prévia do ticker contra a API externa de cotações
- Tickers não encontrados ou inválidos na API não podem ser cadastrados
- Caso a API esteja indisponível no momento do cadastro, o sistema deve informar o usuário e impedir o cadastro até que a validação seja possível

### 7.2 Cálculo de Custo Total

O sistema não calcula preço médio. Em vez disso, mantém o **Custo Total** acumulado de cada ativo:

```
Compra:
Novo Custo Total = Custo Total Atual + Valor Total da Compra

Venda:
Novo Custo Total = Custo Total Atual − ((Custo Total Atual ÷ Qtd Atual) × Qtd Vendida)
```

- Na **compra**, o valor total da operação informado pelo usuário (já incluindo taxas/corretagem) é somado ao custo total acumulado do ativo
- Os campos de preço unitário e taxas, quando informados, são apenas para fins informativos e não alteram o cálculo do Custo Total
- Na **venda**, o custo total é reduzido proporcionalmente à quantidade vendida em relação à quantidade atual, antes da venda
- **Splits e Grupamentos**: o custo total permanece **inalterado**; apenas a quantidade do ativo é ajustada conforme a proporção informada manualmente
- **Bonificações**: o custo total permanece **inalterado**; a quantidade do ativo é incrementada pela quantidade recebida

### 7.3 Cálculo de P&L

- **P&L Não Realizado** = (Quantidade Atual × Cotação Atual) − Custo Total

### 7.4 Eventos Corporativos (Splits, Grupamentos e Bonificações)

- **Splits e Grupamentos** são registrados manualmente pelo usuário, informando: ativo, data, proporção, nova quantidade e descrição (opcional)
- **Bonificações** são registradas manualmente pelo usuário, informando: ativo, data, quantidade recebida e descrição (opcional)
- Em ambos os casos, o sistema **não recalcula** o custo total do ativo — apenas a quantidade é ajustada conforme informado
- Esses eventos são armazenados como registros distintos das operações de compra/venda, mantendo histórico próprio por ativo

### 7.5 Proventos — Registro Manual

O valor total recebido em proventos é informado manualmente pelo usuário e não é calculado pelo sistema. O campo "valor por ação/cota" é opcional e tem caráter puramente informativo, sem influenciar o valor total nem alimentar cálculos automáticos (ex: Dividend Yield).

---

## 8. Fluxos Principais

### 8.1 Fluxo: Cadastrar Ativo

1. Usuário acessa "Ativos" → "Novo Ativo"
2. Informa o ticker do ativo (ação ou FII)
3. Sistema consulta a API externa de cotações para validar o ticker
4. Se o ticker for válido, sistema preenche automaticamente nome, tipo e segmento (quando disponível na API) e permite o usuário ajustar
5. Se o ticker for inválido ou não encontrado, sistema exibe mensagem de erro e impede o cadastro
6. Usuário confirma o cadastro

### 8.2 Fluxo: Registrar Compra de Ativo

1. Usuário acessa "Operações" → "Nova Operação"
2. Seleciona o tipo: Compra
3. Busca o ativo pelo ticker
4. Informa: data, quantidade, valor total da operação (já incluindo taxas/corretagem) e, opcionalmente, o preço unitário e as taxas (apenas informativos)
5. Confirma a operação
6. Sistema atualiza o Custo Total e a posição
7. Dashboard reflete a nova posição

### 8.3 Fluxo: Registrar Evento Corporativo

1. Usuário acessa "Eventos Corporativos" → "Novo Evento"
2. Seleciona o ativo e o tipo de evento: Split/Grupamento ou Bonificação
3. Para Split/Grupamento: informa data, proporção, nova quantidade e descrição (opcional)
4. Para Bonificação: informa data, quantidade recebida e descrição (opcional)
5. Confirma o registro
6. Sistema ajusta a quantidade do ativo conforme informado, mantendo o Custo Total inalterado
7. Evento aparece no histórico de eventos corporativos do ativo

### 8.4 Fluxo: Registrar Provento

1. Usuário acessa "Proventos" → "Novo Provento"
2. Seleciona o ativo e o tipo de provento
3. Informa: data, valor total recebido e, opcionalmente, o valor por ação/cota
4. Confirma o registro
5. Provento aparece no histórico e é somado aos totais do período

### 8.5 Fluxo: Visualizar Dashboard

1. Usuário acessa a tela inicial (Dashboard)
2. Sistema exibe: patrimônio total, alocação, evolução, proventos do mês
3. Usuário pode filtrar por período ou tipo de ativo
4. Usuário pode clicar em um ativo para ver o detalhe de posição e histórico

### 8.6 Fluxo: Visualizar Detalhe do Ativo

1. Usuário acessa "Carteira" e seleciona um ativo
2. Sistema exibe: quantidade atual, Custo Total, valor de mercado atual e P&L não realizado
3. Sistema exibe o histórico de operações (compra/venda) do ativo
4. Sistema exibe o histórico de eventos corporativos (splits, grupamentos, bonificações) do ativo
5. Sistema exibe o histórico de proventos/rendimentos recebidos do ativo
6. Usuário pode visualizar, inserir, editar ou excluir comentários/notas pessoais sobre o ativo
7. Usuário pode acionar diretamente da tela: registrar nova operação de compra/venda, registrar evento corporativo ou registrar provento

---

## 9. Arquitetura de Dados (Modelo Conceitual)

### Entidades Principais

```
Carteira
├── id
├── usuario_id → Usuario
├── nome
├── descricao
└── data_criacao

Ativo
├── id
├── ticker (único)
├── nome
├── tipo (ACAO | FII)
├── setor / segmento
└── ativo (boolean)

Operacao
├── id
├── carteira_id → Carteira
├── ativo_id → Ativo
├── tipo (COMPRA | VENDA)
├── data
├── quantidade
├── valor_total
├── preco_unitario (opcional, apenas informativo)
├── taxas (opcional, apenas informativo)
└── comentario (opcional)

EventoCorporativo
├── id
├── carteira_id → Carteira
├── ativo_id → Ativo
├── tipo (SPLIT | GRUPAMENTO | BONIFICACAO)
├── data
├── proporcao (aplicável a SPLIT/GRUPAMENTO)
├── nova_quantidade (aplicável a SPLIT/GRUPAMENTO)
├── quantidade_recebida (aplicável a BONIFICACAO)
└── descricao (opcional)

Posicao (calculada / materializada)
├── carteira_id → Carteira
├── ativo_id → Ativo
├── quantidade_atual
├── custo_total
└── data_atualizacao

Provento
├── id
├── carteira_id → Carteira
├── ativo_id → Ativo
├── tipo (DIVIDENDO | JCP | RENDIMENTO | AMORTIZACAO)
├── data
├── valor_total
├── valor_por_unidade (opcional)
└── comentario (opcional)

CotacaoAtual (integrada via API, sobrescrita diariamente)
├── ativo_id → Ativo (chave única)
├── data_atualizacao
├── preco
└── fonte (origem da API)

CotacaoMensal (histórico, snapshot mensal)
├── id
├── ativo_id → Ativo
├── ano_mes (ex: 2026-06)
├── preco
└── fonte (origem da API)

ComentarioAtivo
├── id
├── carteira_id → Carteira
├── ativo_id → Ativo
├── texto
├── data_criacao
└── data_atualizacao
```

---

## 10. Stack Tecnológica

### 10.1 Frontend

| Tecnologia | Função |
|------------|--------|
| Angular + TypeScript | Framework principal, estrutura por feature/domínio |
| PrimeNG | Biblioteca de componentes (tabelas, formulários, dropdowns, modais) |
| Tailwind CSS | Utilitário de layout, espaçamento e responsividade (mobile first) |
| Chart.js + ng2-charts | Gráficos do dashboard (evolução do patrimônio, alocação, proventos) |
| Angular Guards | Proteção de rotas autenticadas |
| Angular Interceptors | Injeção de JWT nas requisições e tratamento centralizado de erros HTTP |
| Jasmine + Karma | Testes unitários de componentes e serviços (já incluído no Angular) |

**Estrutura de projeto:** organizada por feature/domínio (carteiras, ativos, operações, eventos corporativos, proventos, dashboard), com serviços Angular separados por entidade e componentes reutilizáveis (tabelas, formulários, modais).

### 10.2 Backend

| Tecnologia | Função |
|------------|--------|
| Java + Spring Boot | Framework principal, REST API |
| Spring Security + JWT | Autenticação e autorização |
| JPA/Hibernate + Spring Data JPA | ORM e abstração de repositórios |
| Flyway | Controle de migrations do banco de dados |
| Bean Validation | Validação de dados de entrada |
| Swagger/OpenAPI | Documentação automática da API |
| JUnit + Mockito | Testes unitários |

**Arquitetura:** em camadas (Controller → Service → Repository), com tratamento de erros centralizado via exception handler global.

### 10.3 Banco de Dados

| Tecnologia | Função |
|------------|--------|
| PostgreSQL | Banco de dados relacional principal |
| Supabase | Hospedagem gerenciada do PostgreSQL (plano gratuito) |

### 10.4 API de Cotações

| Tecnologia | Função |
|------------|--------|
| Brapi | API externa de cotações de ativos da B3 (ações e FIIs), plano gratuito |

### 10.5 Deploy

| Serviço | Função | Custo |
|---------|--------|-------|
| Vercel | Hospedagem do frontend Angular | Gratuito |
| Render | Hospedagem do backend Spring Boot | Gratuito |
| Supabase | Hospedagem do banco PostgreSQL | Gratuito (500MB) |

---

## 11. DevOps

### 11.1 Conteinerização

| Tecnologia | Função |
|------------|--------|
| Docker | Geração de imagens para frontend (Angular) e backend (Spring Boot) |
| Docker Compose | Orquestração do ambiente de desenvolvimento local (frontend + backend + PostgreSQL) |

O Docker Compose permitirá subir todo o ambiente de desenvolvimento com um único comando (`docker-compose up`), garantindo paridade entre os ambientes de cada desenvolvedor.

### 11.2 Ambientes

| Ambiente | Descrição |
|----------|-----------|
| Desenvolvimento | Local, via Docker Compose (Angular + Spring Boot + PostgreSQL em containers) |
| Produção | Vercel (frontend) + Render (backend) + Supabase (banco) |

Cada ambiente terá suas próprias variáveis de ambiente isoladas, incluindo: URL da API Brapi, credenciais do banco de dados, chave secreta JWT e URLs de frontend/backend.

### 11.3 CI/CD

| Tecnologia | Função |
|------------|--------|
| GitHub Actions | Pipeline de integração e entrega contínua, gratuito para repositórios públicos e privados |

**Pipeline de CI (a cada push/pull request):**
1. Checkout do código
2. Build do frontend (Angular) e backend (Spring Boot)
3. Execução dos testes automatizados (Jasmine/Karma no frontend, JUnit/Mockito no backend)
4. Análise de qualidade de código (ESLint no frontend, Checkstyle no backend)
5. Build das imagens Docker

**Pipeline de CD (a cada merge na branch principal):**
1. Deploy automático do frontend no Vercel
2. Deploy automático do backend no Render
3. Execução das migrations do banco via Flyway

### 11.4 Qualidade de Código

| Tecnologia | Camada | Função |
|------------|--------|--------|
| ESLint | Frontend | Análise estática de código TypeScript/Angular (já incluído no Angular) |
| Checkstyle | Backend | Padronização e análise estática do código Java |
| JUnit + Mockito | Backend | Testes unitários executados no pipeline CI |
| Jasmine + Karma | Frontend | Testes unitários executados no pipeline CI |

---

## 12. Roadmap de Entregas

### Fase 0 — Setup e Infraestrutura (1–2 semanas)

- [ ] Criação e configuração do repositório GitHub
- [ ] Configuração do ambiente Docker e Docker Compose (frontend + backend + PostgreSQL)
- [ ] Configuração das contas de deploy (Vercel, Render, Supabase)
- [ ] Configuração das variáveis de ambiente por ambiente (desenvolvimento e produção)
- [ ] Configuração dos pipelines CI/CD com GitHub Actions (build, testes, deploy)
- [ ] Configuração do banco de dados PostgreSQL no Supabase e conexão com o backend
- [ ] Setup inicial do projeto Angular (estrutura de módulos, PrimeNG, Tailwind, ESLint)
- [ ] Setup inicial do projeto Spring Boot (estrutura em camadas, Flyway, Swagger, Checkstyle)

### Fase 1 — MVP (8–12 semanas)

- [ ] Autenticação (cadastro e login de usuário)
- [ ] Gestão de carteiras (criar, editar, excluir múltiplas carteiras)
- [ ] Cadastro de ativos (Ações e FIIs) com validação de ticker via API Brapi
- [ ] Integração com API de cotações (Brapi) — atualização diária e sob demanda
- [ ] Registro de operações (compra, venda)
- [ ] Registro de eventos corporativos (splits, grupamentos, bonificações)
- [ ] Cálculo de posição e Custo Total
- [ ] Registro de proventos
- [ ] Tela de detalhe do ativo (históricos, comentários e atalhos de registro)
- [ ] Dashboard básico com patrimônio e alocação

### Fase 2 — Consolidação (4–6 semanas após MVP)

- [ ] Gráfico de evolução do patrimônio (baseado em snapshots mensais de cotação)
- [ ] Relatório de proventos por período
- [ ] P&L não realizado
- [ ] Visão consolidada multi-carteira

### Fase 3 — Evolução (futuro)

- [ ] Importação de nota de corretagem (PDF)
- [ ] Cálculo de Dividend Yield por ativo (baseado em proventos informados)
- [ ] Suporte a BDRs e ETFs
- [ ] Relatório para IR
- [ ] Exportação de dados (CSV, PDF)
- [ ] App mobile nativo

---

## 13. Critérios de Aceitação do MVP

| Feature | Critério |
|---------|----------|
| Cadastro de Ativo | Sistema valida o ticker via API externa; tickers válidos são cadastrados, inválidos são rejeitados com mensagem de erro |
| Compra | Após registrar compra, Custo Total é incrementado pelo valor total informado (já incluindo taxas) e a quantidade aumenta |
| Venda Parcial | Custo Total é reduzido proporcionalmente: Novo Custo Total = Custo Total Atual − ((Custo Total Atual ÷ Qtd Atual) × Qtd Vendida) |
| Split/Grupamento/Bonificação | Quantidade é ajustada conforme informado pelo usuário; Custo Total permanece inalterado |
| Provento | Valor total recebido é o informado manualmente pelo usuário, sem cálculo automático |
| Dashboard | Patrimônio total = soma de (qtd × cotação) de todos os ativos |

---

## 14. Riscos e Mitigações

| Risco | Probabilidade | Impacto | Mitigação |
|-------|--------------|---------|-----------|
| Complexidade de eventos corporativos | Alta | Alto | Implementar e testar cada tipo de evento com casos reais |
| Imprecisão no cálculo de Custo Total | Média | Alto | Validar contra cálculos manuais e casos de teste automatizados |
| Cotação desatualizada | Alta | Médio | Permitir cotação manual no MVP; integrar API na Fase 3 |
| Escopo crescente (feature creep) | Alta | Médio | Manter backlog priorizado; novos itens entram apenas em fases futuras |
| Baixa adoção por complexidade da UI | Baixa | Alto | Realizar testes de usabilidade com 3–5 usuários antes do lançamento |

---

## 15. Glossário

| Termo | Definição |
|-------|-----------|
| Ticker | Código de negociação do ativo na B3 (ex: PETR4, KNRI11) |
| FII | Fundo de Investimento Imobiliário |
| Custo Total | Soma acumulada dos valores pagos na aquisição de um ativo, ajustada por vendas |
| Split | Desdobramento de ações/cotas, registrado manualmente, ajustando apenas a quantidade |
| Grupamento (Inplit) | Agrupamento de ações/cotas, registrado manualmente, ajustando apenas a quantidade |
| Bonificação | Distribuição de novas ações/cotas, registrada manualmente, incrementando a quantidade sem alterar o Custo Total |
| P&L | Profit & Loss — Lucro ou Prejuízo |
| DY | Dividend Yield — rendimento sobre o preço atual (planejado para Fase 3) |
| JCP | Juros sobre Capital Próprio |

---

*Documento gerado em Junho de 2026. Versão sujeita a revisão conforme feedback das partes interessadas.*