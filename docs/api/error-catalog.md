# Error Catalog

Todas as excecoes de negocio devem usar um destes codigos.

| Codigo | HTTP | Mensagem PT-BR | Quando disparar |
|---|---:|---|---|
| ERR-001 | 401 | Autenticacao obrigatoria. | Token ausente, invalido ou expirado sem refresh possivel |
| ERR-002 | 403 | Acesso negado ao recurso informado. | Recurso pertence a outro usuario |
| ERR-003 | 422 | Ticker invalido ou nao encontrado. | Brapi retorna vazio ou ativo nao validavel |
| ERR-004 | 503 | Servico de cotacoes indisponivel. | Brapi indisponivel durante cadastro/validacao |
| ERR-005 | 409 | Ja existe um ativo com este ticker. | Duplicidade de ticker |
| ERR-006 | 422 | Quantidade de venda maior que a posicao atual. | Venda acima do saldo |
| ERR-007 | 422 | Valor informado deve ser maior que zero. | Quantidade ou valores invalidos |
| ERR-008 | 422 | Nao e possivel excluir a ultima carteira. | Regra RN-10 |
| ERR-009 | 409 | Carteira possui registros vinculados. | Regra RN-09 |
| ERR-010 | 404 | Recurso nao encontrado. | ID inexistente ou inacessivel |
| ERR-011 | 400 | Dados de entrada invalidos. | Bean Validation ou JSON invalido |
| ERR-012 | 409 | Email ja cadastrado. | Registro de usuario duplicado |
| ERR-013 | 422 | Alteracao invalida: o historico ficaria com quantidade negativa. | Edicao ou exclusao retroativa torna a linha do tempo invalida |
