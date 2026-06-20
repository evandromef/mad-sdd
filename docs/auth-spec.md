# Auth Spec

## Registro

- Endpoint: `POST /auth/register`
- Campos: nome, email, senha.
- Email deve ser unico.
- Senha deve ter no minimo 8 caracteres.
- Senha deve ser armazenada com BCrypt strength 12.
- Ao registrar, criar uma carteira padrao para o usuario.
- Resposta: access token, refresh token e token type.

## Login

- Endpoint: `POST /auth/login`
- Campos: email, senha.
- Em sucesso, retornar access token JWT e refresh token.
- Em falha, retornar 401 com `ERR-001`.

## Refresh

- Endpoint: `POST /auth/refresh`
- Recebe refresh token valido e retorna novo access token.
- Refresh token deve ter expiracao maior que access token.

## Logout

- Invalidar refresh token no servidor.
- Frontend deve remover tokens locais.

## Frontend

- AuthInterceptor adiciona `Authorization: Bearer <token>`.
- Em 401, tentar refresh uma vez antes de redirecionar ao login.
- Guards protegem todas as rotas autenticadas.

