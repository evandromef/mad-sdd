# ADR-002: Banco de Dados e Persistência

- **Data:** 06/07/2026
- **Responsável:** Evandro Moreira

## Contexto

Operações financeiras exigem transações ACID, integridade referencial, precisão decimal, auditoria e consultas previsíveis. O dashboard também precisa responder sem recalcular todo o histórico a cada leitura.

## Decisão

- PostgreSQL 18 hospedado no Aiven, com instância separada por ambiente.
- UUID como chave primária.
- JPA/Hibernate para persistência e Flyway para evolução do schema.
- `NUMERIC(19,8)` para dinheiro e quantidade; `NUMERIC(19,10)` para fatores e percentuais.
- `posicao_ativo` materializa quantidade e Custo Total, sem preço médio.
- Alterações históricas reconstroem cronologicamente a posição do par carteira/ativo na mesma transação.
- Exclusões são físicas.
- `registro_auditoria` mantém eventos imutáveis de criação, alteração e exclusão, com estado aplicável em JSON e identidade anonimizada após exclusão da conta.

## Alternativas consideradas

| Alternativa | Motivo da rejeição |
| --- | --- |
| MySQL | PostgreSQL foi escolhido pela precisão, recursos de consulta e padronização definida para o projeto. |
| MongoDB | Relações e invariantes financeiras favorecem modelo relacional e transações ACID. |
| Posição somente sob demanda | Aumenta o custo recorrente das consultas do dashboard. |
| Soft delete | A decisão exige exclusão física e atendimento à eliminação definitiva de dados pessoais. |

## Consequências

- Escritas e alterações retroativas ficam mais complexas e exigem bloqueio/controle de versão.
- A projeção deve ser reconstruível e testada contra o histórico.
- Auditoria não pode conter senha, token, segredo ou dado pessoal desnecessário.
- Backup e recuperação permanecem decisão pendente antes da produção.

## Referências

- [ERS](../../requisitos/ers.md): RNF-005, RNF-007, RNF-013 e RN-001 a RN-018
- [Modelo de Dados](../modelo_dados.md)
- [Arquitetura e Stack](../documento_arquitetura_MAD.md)
