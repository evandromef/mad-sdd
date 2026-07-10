# ADR-001: Estilo Arquitetural

- **Data:** 07/07/2026
- **Responsável:** Evandro Moreira
- **Documento relacionado:** ERS - RNF-001, RNF-009

## Contexto

O MVP possui um único tipo de usuário e domínios relacionados, mas frontend e API precisam de ciclos de entrega independentes. Microsserviços adicionariam custo operacional sem necessidade demonstrada. Sistema em desenvolvimento por uma única pessoa com apoio de agente de IA (Claude Code / Codex).

## Decisão

- Frontend Angular e API Spring Boot serão aplicações e contêineres separados.
- A API será um monolito modular, organizado por domínio (`carteira`, `operacao`, `provento`, `bonificacao`, `eventocorporativo`, `cotacao`, `usuario`), cada um com suas próprias camadas internas (controller, service, repository, dto).
- Não é utilizado Spring Modulith.
- Módulos se comunicam por serviços públicos explícitos; entidades e repositórios permanecem internos ao domínio proprietário.
- A API será stateless em relação à sessão de acesso.

## Alternativas consideradas

| Alternativa | Motivo da rejeição |
| --- | --- |
| Monolito sem módulos | Eleva acoplamento e dificulta evolução por domínio. |
| Spring Modulith | Dependência e convenções adicionais não desejadas para o projeto. |
| Microsserviços | Complexidade de rede, consistência, observabilidade e deploy desproporcional ao MVP. |

## Consequências

- Um único deploy da API simplifica transações financeiras.
- Limites modulares dependem de convenções, testes arquiteturais e revisão de código.
- Pacotes de domínio devem se comunicar apenas via interfaces de serviço, evitando acesso direto a repositórios de outro domínio — isso preserva a possibilidade de extração futura em serviços separados, caso seja necessário.
- Escala horizontal ocorre replicando a API inteira.
- Extração futura de módulo exige novo ADR.

## Referências

- ERS: RNF-001 e RNF-009
- [Documento de Arquitetura](../documento_arquitetura_MAD.md)
