# Test Scenarios

Estes cenarios orientam testes JUnit, Jasmine e integracao. Valores monetarios esperados usam 2 casas decimais.

| ID | Cenario | Entrada | Saida esperada |
|---|---|---|---|
| CT-01 | Compra inicial | 10 PETR4 por R$ 2.500,00 | CT R$ 2.500,00; qtd 10 |
| CT-02 | Segunda compra | +5 PETR4 por R$ 1.200,00 | CT R$ 3.700,00; qtd 15 |
| CT-03 | Venda parcial | Venda de 5 PETR4 | CT R$ 2.466,67; qtd 10 |
| CT-04 | Split 1:2 | Nova qtd 20 | CT R$ 2.466,67; qtd 20 |
| CT-05 | Bonificacao | +3 unidades | CT R$ 2.466,67; qtd 23 |
| CT-06 | Ticker invalido | `XXXXX` | HTTP 422, ERR-003 |
| CT-07 | Excluir compra | Remove primeira compra | Recalculo completo do historico remanescente |
| CT-08 | Dashboard vazio | Carteira sem ativos | Patrimonio R$ 0,00 sem erro |
| CT-09 | API Brapi fora | Timeout na atualizacao | Mantem ultima cotacao; marca desatualizada |
| CT-10 | Venda maior que saldo | Vender 20 com saldo 10 | HTTP 422, ERR-006 |
| CT-11 | Excluir ultima carteira | Usuario tem 1 carteira | HTTP 422, ERR-008 |
| CT-12 | Provento manual | Valor total R$ 123,45 | Salva exatamente R$ 123,45 |
| CT-13 | Split nao altera CT | CT R$ 1.000,00, qtd 10 -> 100 | CT R$ 1.000,00; qtd 100 |
| CT-14 | P&L positivo | qtd 10, cotacao 30, CT 250 | P&L R$ 50,00 |
| CT-15 | Cotacao mensal | Ativo ja tem snapshot 2026-06 | Sobrescreve snapshot do mes |
| CT-16 | Venda zera posicao | Qtd 10, CT R$ 2.500,00; venda 10 | Qtd 0; CT R$ 0,00 |
| CT-17 | Carteira ativa omite zerados | PETR4 com qtd 0; `incluirZeradas=false` | PETR4 nao aparece na listagem de posicoes |
| CT-18 | Carteira inclui zerados por filtro | PETR4 com qtd 0; `incluirZeradas=true` | PETR4 aparece com qtd 0 e CT R$ 0,00 |
| CT-19 | Edicao retroativa invalida | Excluir compra antiga que sustentava venda posterior | HTTP 422, ERR-013 |
| CT-20 | Evento antes de operacao no mesmo dia | Split para 20 e venda de 5 na mesma data | Aplicar split antes da venda; qtd final 15 |
