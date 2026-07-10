# ADR-003: Login Social Google

- **Data:** 06/07/2026
- **Responsável:** Evandro Moreira

## Contexto

O MAD deve autenticar com Google por OAuth 2.0/OIDC e vincular uma identidade Google à conta tradicional existente quando os e-mails verificados coincidirem.

## Decisão

- Usar Spring Security OAuth2 Client com Authorization Code Flow e PKCE quando aplicável ao cliente público.
- O backend valida emissor, audiência, assinatura, expiração, `state`, `nonce` e `email_verified`.
- Buscar primeiro a identidade por `issuer` e `subject`; na ausência, vincular à conta com o mesmo e-mail normalizado e verificado; criar conta apenas quando nenhuma existir.
- Persistir `issuer`, `subject` e e-mail verificado. Não persistir senha Google nem tokens de longa duração desnecessários.
- Após autenticação, emitir a mesma sessão local definida no ADR-005.

## Alternativas consideradas

| Alternativa | Motivo da rejeição |
| --- | --- |
| Implementar OIDC manualmente | Eleva risco de falhas de protocolo e segurança. |
| Supabase Auth/Auth0/Firebase | Adiciona fornecedor e duplica a gestão de identidade já mantida no backend. |

## Consequências

- Credenciais do cliente Google são segredos por ambiente.
- Mudança de e-mail no Google não altera automaticamente a propriedade dos dados.
- Conflitos de vínculo devem falhar sem criar conta duplicada e gerar auditoria segura.

## Referências

- ERS: RF-003, RNF-004 e RN-011
- [ADR-005](./adr-005-estrategia-autenticacao.md)
