# EPICO-02 - Conta e Acesso

## Objetivo

Permitir que o investidor crie conta, autentique-se, recupere acesso e gerencie seus dados pessoais com seguranca.

## Requisitos relacionados

- RF-001 a RF-005
- RN-011
- RNF-002 a RNF-006, RNF-010, RNF-011, RNF-013, RNF-017
- ESPEC-01
- ADR-003, ADR-005

## Escopo

- Cadastro com e-mail e senha.
- Confirmacao por e-mail.
- Login tradicional.
- Login Google OIDC.
- Recuperacao de senha.
- Perfil do usuario.
- Exclusao definitiva da conta.
- JWT e refresh token.

## Fora do escopo

- Multiplos perfis.
- Compartilhamento de carteiras.

## Sprints relacionadas

- Sprint 1

## Criterios de conclusao

- Usuario consegue criar conta, entrar, recuperar senha e excluir conta.
- Dados de outros usuarios permanecem inacessiveis.
- Senhas e tokens sao persistidos apenas como hash quando aplicavel.

## Riscos e dependencias

- Provedor de e-mail.
- Configuracao Google OIDC.
- Definicao pendente de politica minima de senha.

## Status

- Planejado
