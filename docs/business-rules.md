# Business Rules

Fonte de verdade para calculos financeiros e validacoes. Em caso de conflito, estes IDs devem aparecer nos testes e nos erros.

## Regras

| ID | Regra | Comportamento |
|---|---|---|
| RN-01 | Custo Total na compra | `novo_ct = ct_atual + valor_total_operacao` |
| RN-02 | Custo Total na venda | `novo_ct = ct_atual - ((ct_atual / qtd_atual) * qtd_vendida)` |
| RN-03 | Split ou grupamento | `quantidade_atual = nova_quantidade`; Custo Total inalterado |
| RN-04 | Bonificacao | `quantidade_atual += quantidade_recebida`; Custo Total inalterado |
| RN-05 | P&L nao realizado | `(quantidade_atual * cotacao_atual) - custo_total` |
| RN-06 | Validacao de ticker | Consultar Brapi antes de cadastrar ativo; se invalido ou API indisponivel, bloquear cadastro |
| RN-07 | Edicao/exclusao de operacao | Recalcular posicao do zero a partir do historico remanescente em ordem cronologica |
| RN-08 | Edicao/exclusao de evento | Recalcular posicao do zero a partir do historico remanescente em ordem cronologica |
| RN-09 | Exclusao de carteira | Permitida somente se nao houver registros vinculados |
| RN-10 | Ultima carteira | Nunca excluir a ultima carteira do usuario |
| RN-11 | Cotacao desatualizada | Exibir indicador se `data_atualizacao < data atual local` ou se ultima atualizacao falhou |
| RN-12 | Precisao monetaria | Usar BigDecimal; respostas monetarias em BRL com 2 casas |
| RN-13 | Venda com valor informado | Em vendas, `valor_total` registra o valor financeiro recebido informado pelo usuario, mas nao altera diretamente o Custo Total |
| RN-14 | Posicao zerada | Se quantidade atual chegar a zero, o ativo sai da carteira ativa por padrao; historico permanece consultavel |
| RN-15 | Historico nao negativo | Edicoes e exclusoes devem ser rejeitadas se o recalcule do historico produzir quantidade negativa em qualquer ponto |

## Calculo Sob Demanda de Posicao

Nao existe tabela `posicoes`. O `PosicoesService` deve:

1. Buscar operacoes e eventos do par `(carteira_id, ativo_id)`.
2. Ordenar por `data`, depois por prioridade: eventos corporativos antes de operacoes de compra/venda no mesmo dia, depois `data_criacao` e `id`.
3. Aplicar RN-01 a RN-04 sequencialmente.
4. Retornar um DTO calculado com quantidade atual, custo total, valor de mercado e P&L.

Se o recalcule produzir quantidade negativa em qualquer ponto da linha do tempo, a operacao de criacao, edicao ou exclusao deve ser rejeitada com HTTP 422. Se a quantidade final for zero, o Custo Total deve ser forcado para `0.00` para evitar residuo de arredondamento.

## Validacoes Minimas

- Venda com quantidade maior que o saldo deve retornar erro 422.
- Compra, venda, bonificacao e nova quantidade devem ser maiores que zero.
- Venda com quantidade atual zero deve retornar erro 422.
- Alteracoes retroativas que tornem o historico invalido devem retornar erro 422.
- Ticker deve ser unico por ativo cadastrado.
- Carteira deve pertencer ao usuario autenticado.

## Papel do Valor Total em Compras e Vendas

- Em compras, `valor_total` representa o capital investido e incrementa o Custo Total conforme RN-01.
- Em vendas, `valor_total` representa o valor recebido informado pelo usuario para historico e relatorios futuros. Ele nao entra na formula RN-02.
- O Custo Total da venda sempre e baixado proporcionalmente pela quantidade vendida.
- Se a venda zerar a quantidade do ativo, a quantidade final deve ser `0` e o Custo Total final deve ser `0.00`.

## Posicoes Zeradas

Ativos com quantidade atual igual a zero nao aparecem na carteira ativa por padrao. O sistema deve preservar todo o historico de operacoes, eventos, proventos e comentarios. Telas de historico podem oferecer filtro para "mostrar ativos zerados".

## Exemplos Numericos

### Compra inicial

- Entrada: 10 PETR4, valor total R$ 2.500,00
- Saida: quantidade 10, Custo Total R$ 2.500,00

### Segunda compra

- Estado: quantidade 10, CT R$ 2.500,00
- Entrada: +5 PETR4, valor total R$ 1.200,00
- Saida: quantidade 15, CT R$ 3.700,00

### Venda parcial

- Estado: quantidade 15, CT R$ 3.700,00
- Entrada: venda de 5
- Custo baixado: `(3700 / 15) * 5 = 1233,33`
- Saida: quantidade 10, CT R$ 2.466,67

### Split

- Estado: quantidade 10, CT R$ 2.466,67
- Entrada: nova quantidade 20
- Saida: quantidade 20, CT R$ 2.466,67

### Bonificacao

- Estado: quantidade 20, CT R$ 2.466,67
- Entrada: +3 unidades
- Saida: quantidade 23, CT R$ 2.466,67
