# MAD - Sistema de Gestao de Investimentos

Aplicacao web para investidores pessoa fisica controlarem carteiras de acoes e FIIs.
O sistema substitui planilhas por cadastro estruturado de ativos, operacoes, eventos corporativos, proventos, cotacoes e dashboards.
Todas as implementacoes devem seguir as specs em `docs/` antes de gerar codigo.

## Log de Atividades

- Todas as atividades realizadas no projeto devem ser registradas em `logs/atividades/`.
- Deve existir um arquivo por dia, usando o formato `YYYY-MM-DD.md`.
- Cada registro deve conter, no minimo, horario aproximado, resumo da atividade e arquivos alterados quando houver.
- O log deve ser atualizado ao final de cada atividade relevante.

## Persistencia da Posicao

- A quantidade atual e o Custo Total por ativo e carteira podem ser mantidos em tabela materializada.
- A tabela materializada deve ser atualizada de forma transacional sempre que uma operacao, bonificacao ou evento corporativo for criado, alterado ou excluido.
- Nunca calcular nem persistir preco medio como regra de negocio.
- Eventos corporativos e bonificacoes alteram quantidade, nunca o Custo Total.

## Documentação de referência

Antes de implementar código, consulte as especificações em `docs/`, especialmente:

- `docs/requisitos/ers.md`
- `docs/requisitos/escopo_mvp.md`
- `docs/arquitetura/documento_arquitetura_MAD.md`
- `docs/arquitetura/modelo_dados.md`
- `docs/integracoes/integracao_api_bolsa_brasil.md`
- `docs/requisitos/especs/`
