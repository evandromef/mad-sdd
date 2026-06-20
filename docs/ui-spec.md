# UI Spec

Frontend Angular 21 LTS com Node.js 24 LTS, PrimeNG, Tailwind CSS, Reactive Forms e Chart.js.

## Layout Base

- App autenticado com topbar compacta, menu lateral responsivo e seletor de carteira sempre visivel.
- O seletor deve oferecer carteiras individuais e opcao "Todas as carteiras" quando a tela suportar consolidado.
- Feedback de erro deve usar mensagens do catalogo `ERR-XXX`.
- Tabelas devem ter ordenacao, filtro por ticker/nome e estado vazio.

## Login e Cadastro

- Campos login: email, senha.
- Campos cadastro: nome, email, senha.
- Validacoes: email valido, senha com minimo 8 caracteres.
- Ao login bem sucedido, salvar tokens via AuthService e navegar para dashboard.

## Dashboard

- Cards: patrimonio total, P&L total, proventos do mes, maior posicao.
- Graficos: donut de alocacao por ativo, donut/barra por classe, linha de evolucao mensal, barras de proventos mensais.
- Estado vazio: patrimonio zero e graficos substituidos por mensagens discretas.

## Carteira e Posicoes

- Tabela com colunas: ticker, nome, classe, quantidade, Custo Total, cotacao, valor de mercado, P&L, percentual.
- Separar visualmente Acoes e FIIs.
- Por padrao, nao exibir posicoes com quantidade atual zero.
- Disponibilizar toggle "Mostrar zerados" que chama a API com `incluirZeradas=true`.
- Posicoes zeradas, quando exibidas, devem ter quantidade `0`, Custo Total `0,00` e indicacao visual discreta de posicao encerrada.
- Badge de cotacao desatualizada conforme RN-11.
- Clique no ticker abre Detalhe do Ativo.

## Detalhe do Ativo

- Cabecalho com ticker, nome, classe, quantidade, Custo Total, valor de mercado e P&L.
- Se a posicao estiver zerada, exibir estado "posicao encerrada" sem remover historico.
- Acoes rapidas: registrar compra/venda, registrar evento corporativo, registrar provento.
- Tabs PrimeNG: Operacoes, Eventos, Proventos, Notas.
- Notas com CRUD inline simples.

## Operacoes

- Campos: tipo, ativo, data, quantidade, valor total, preco unitario opcional, taxas opcionais, comentario opcional.
- Em compras, valor total alimenta Custo Total.
- Em vendas, valor total representa o valor recebido informado pelo usuario e nao altera diretamente o Custo Total.
- Preco unitario e taxas sao informativos.
- Venda deve validar saldo antes de confirmar.
- Se uma venda zerar a posicao, a carteira ativa deve deixar de exibir o ativo apos salvar, exceto se "Mostrar zerados" estiver ativo.

## Eventos Corporativos

- Campos comuns: ativo, tipo, data, descricao opcional.
- Split/grupamento: proporcao opcional e nova quantidade obrigatoria.
- Bonificacao: quantidade recebida obrigatoria.
- Texto de ajuda deve deixar claro que Custo Total nao muda.

## Proventos

- Campos: ativo, tipo, data, valor total recebido, valor por unidade opcional, comentario opcional.
- Valor total sempre manual.
- Lista com filtros por periodo e totalizadores mensal, trimestral e anual.

## Gestao de Carteiras

- CRUD em tela simples.
- Exclusao exige confirmacao.
- Se houver registros vinculados ou for a ultima carteira, exibir erro conforme catalogo.

## Cadastro de Ativos

- Campo ticker com validacao assincrona via `/ativos/validar`.
- Quando valido, preencher nome, tipo, setor e segmento quando disponiveis.
- Quando Brapi estiver indisponivel, bloquear cadastro.
