# ADR-005: Estratégia de Autenticação

- **Status:** Proposto
- **Data:** 06/07/2026
- **Responsável:** Evandro Moreira

## Contexto

A aplicação exige Bearer JWT, renovação automática, revogação controlada, senha protegida e recuperação de senha de uso único.

## Decisão

- Access token JWT assinado, válido por 15 minutos, mantido somente em memória no Angular e enviado como Bearer.
- Refresh token opaco e aleatório, válido por 7 dias, entregue em cookie `HttpOnly` e `Secure`.
- Persistir apenas hash do refresh token; rotacionar a cada uso e detectar reutilização.
- Revogar a família de tokens no logout, troca de senha, reutilização detectada e exclusão da conta.
- Usar Argon2id para hash de senha. Parâmetros de memória, iterações e paralelismo serão calibrados antes da implementação e registrados na configuração.
- Tokens de confirmação e recuperação são aleatórios, de uso único e persistidos como hash; recuperação expira em 30 minutos.
- Resend envia mensagens usando remetente configurado por `EMAIL_FROM`.

## Alternativas consideradas

| Alternativa | Motivo da rejeição |
| --- | --- |
| JWT em armazenamento persistente do navegador | Amplia exposição em caso de XSS. |
| Refresh JWT não persistido | Dificulta rotação, revogação e detecção de reutilização. |
| Sessão integral no servidor | Contraria a escolha de Bearer JWT para acesso à API. |
| bcrypt | Argon2id foi escolhido por resistência configurável a ataques com hardware especializado. |

## Consequências

- O frontend precisa recuperar a sessão após recarregamento usando o cookie de refresh.
- Domínios separados exigem CORS, atributos de cookie e proteção CSRF configurados por ambiente.
- Chaves de assinatura precisam de rotação operacional futura.
- Endpoints de autenticação recebem rate limiting.

## Referências

- ERS: RF-001, RF-002, RF-004, RNF-002 e RNF-003
- [ADR-003](./adr-003-provedor-oauth.md)
