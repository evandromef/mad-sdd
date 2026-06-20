# language: pt
Funcionalidade: Calculo de posicao e custo total

  Cenario: CT-01 compra inicial
    Dado uma carteira vazia
    Quando registro compra de 10 PETR4 por "2500.00"
    Entao a quantidade de PETR4 deve ser "10"
    E o custo total deve ser "2500.00"

  Cenario: CT-03 venda parcial
    Dado uma posicao de 15 PETR4 com custo total "3700.00"
    Quando registro venda de 5 PETR4
    Entao a quantidade de PETR4 deve ser "10"
    E o custo total deve ser "2466.67"

  Cenario: CT-04 split nao altera custo total
    Dado uma posicao de 10 PETR4 com custo total "2466.67"
    Quando registro split com nova quantidade 20
    Entao a quantidade de PETR4 deve ser "20"
    E o custo total deve ser "2466.67"

  Cenario: CT-05 bonificacao nao altera custo total
    Dado uma posicao de 20 PETR4 com custo total "2466.67"
    Quando registro bonificacao de 3 PETR4
    Entao a quantidade de PETR4 deve ser "23"
    E o custo total deve ser "2466.67"

  Cenario: CT-16 venda zera posicao
    Dado uma posicao de 10 PETR4 com custo total "2500.00"
    Quando registro venda de 10 PETR4
    Entao a quantidade de PETR4 deve ser "0"
    E o custo total deve ser "0.00"

  Cenario: CT-20 evento corporativo vem antes de operacao no mesmo dia
    Dado uma posicao de 10 PETR4 com custo total "2500.00"
    Quando registro split de PETR4 com nova quantidade 20 na data "2026-06-15"
    E registro venda de 5 PETR4 na data "2026-06-15"
    Entao o sistema deve aplicar o evento corporativo antes da venda
    E a quantidade de PETR4 deve ser "15"

  Cenario: CT-19 alteracao retroativa que torna historico negativo
    Dado uma compra de 10 PETR4
    E uma venda posterior de 8 PETR4
    Quando tento excluir a compra de 10 PETR4
    Entao a API deve retornar HTTP 422
    E o codigo de erro deve ser "ERR-013"

Funcionalidade: Listagem de posicoes

  Cenario: CT-17 carteira ativa omite posicoes zeradas
    Dado uma posicao zerada de PETR4
    Quando listo posicoes com "incluirZeradas" igual a "false"
    Entao PETR4 nao deve aparecer na listagem

  Cenario: CT-18 carteira inclui posicoes zeradas por filtro
    Dado uma posicao zerada de PETR4
    Quando listo posicoes com "incluirZeradas" igual a "true"
    Entao PETR4 deve aparecer na listagem
    E a quantidade de PETR4 deve ser "0"
    E o custo total deve ser "0.00"

Funcionalidade: Validacao de ativos

  Cenario: CT-06 ticker invalido
    Dado que a Brapi nao encontra o ticker "XXXXX"
    Quando tento cadastrar o ativo "XXXXX"
    Entao a API deve retornar HTTP 422
    E o codigo de erro deve ser "ERR-003"

  Cenario: API indisponivel durante cadastro
    Dado que a Brapi esta indisponivel
    Quando tento cadastrar um novo ativo
    Entao a API deve retornar HTTP 503
    E o codigo de erro deve ser "ERR-004"

Funcionalidade: Carteiras

  Cenario: CT-11 nao excluir ultima carteira
    Dado um usuario com apenas uma carteira
    Quando tento excluir essa carteira
    Entao a API deve retornar HTTP 422
    E o codigo de erro deve ser "ERR-008"

Funcionalidade: Dashboard

  Cenario: CT-08 dashboard vazio
    Dado uma carteira sem ativos
    Quando acesso o dashboard
    Entao o patrimonio total deve ser "0.00"
    E nenhum erro deve ser exibido

Funcionalidade: Cotacoes

  Cenario: CT-09 falha na atualizacao de cotacao
    Dado uma cotacao anterior valida
    E que a Brapi falha durante a atualizacao
    Quando atualizo as cotacoes
    Entao a ultima cotacao valida deve ser mantida
    E a cotacao deve ser marcada como desatualizada

Funcionalidade: Proventos

  Cenario: CT-12 provento manual
    Dado uma posicao existente de KNRI11
    Quando registro provento de valor total "123.45"
    Entao o valor total salvo deve ser "123.45"
