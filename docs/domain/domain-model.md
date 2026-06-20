# Domain Model

## Usuario

Representa uma conta autenticada. Possui carteiras e credenciais.

## Carteira

Agrupa operacoes, eventos, proventos e comentarios de um usuario.
Uma carteira sempre pertence a um unico usuario.

## Ativo

Representa uma acao ou FII negociado na B3.
Ticker e unico globalmente.
Ativo pode ser desativado, mas nao deve ser removido fisicamente quando houver historico.

## Operacao

Registro historico de compra ou venda.
Compra aumenta quantidade e Custo Total.
Venda reduz quantidade e reduz Custo Total proporcionalmente.
Em venda, `valor_total` registra o valor recebido informado pelo usuario, mas nao altera diretamente o Custo Total.

## EventoCorporativo

Registro historico separado das operacoes.
Split e grupamento definem nova quantidade.
Bonificacao incrementa quantidade.
Nenhum evento altera Custo Total.

## Provento

Registro manual de valor recebido.
Nao participa do calculo de Custo Total nem de P&L nao realizado.

## CotacaoAtual

Ultimo preco conhecido de um ativo.
Pode estar desatualizada quando a atualizacao falhar ou quando a data for anterior ao dia atual.

## CotacaoMensal

Snapshot mensal usado para evolucao historica de patrimonio.

## ComentarioAtivo

Nota livre do usuario para um ativo dentro de uma carteira.

## PosicaoDTO

Valor calculado, nao Entity.
Derivado do historico de Operacao e EventoCorporativo.
Campos: quantidadeAtual, custoTotal, valorMercado, pnlNaoRealizado, percentualAlocacao.
Quando quantidadeAtual for zero, custoTotal deve ser zero e a posicao e considerada encerrada.
Listagens omitem posicoes encerradas por padrao, exceto quando solicitado por `incluirZeradas=true`.
O historico nunca pode produzir quantidade negativa em nenhum ponto da linha do tempo.
