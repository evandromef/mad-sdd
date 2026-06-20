# Frontend Architecture

## Principios

- Angular 21 LTS com Node.js 24 LTS e standalone components ou modulos por dominio, conforme scaffold escolhido.
- Estado remoto via services HTTP e RxJS.
- Estado local de tela com Signals quando simplificar leitura.
- Reactive Forms em todos os formularios.
- PrimeNG para componentes ricos; Tailwind para layout e espaco.

## Pastas

```text
frontend/src/app/
  auth/
  carteiras/
  ativos/
  operacoes/
  eventos/
  proventos/
  dashboard/
  core/
  shared/
```

## HTTP

- `AuthInterceptor`: injeta JWT.
- `ErrorInterceptor`: traduz `ERR-XXX` para mensagens de UI.
- `CacheInterceptor`: cache HTTP de 60s para endpoints GET de posicoes e dashboard.
- Services de posicao devem aceitar opcao `incluirZeradas`, default `false`, e repassar como query param para `/carteiras/{cid}/posicoes` e `/posicoes/consolidado`.
- A chave de cache HTTP deve considerar o valor de `incluirZeradas`, para evitar misturar listagens com e sem posicoes zeradas.

## Formularios

- Usar `FormBuilder`, `FormGroup`, `Validators`.
- Validacao assincrona de ticker no cadastro de ativos.
- Campos monetarios devem enviar strings decimais para preservar precisao.
- Formularios de operacao devem tratar `ERR-013` como alteracao retroativa invalida e orientar o usuario a revisar o historico do ativo.

## Estado de Posicoes

- A tela de carteira mantem estado local para o toggle "Mostrar zerados".
- Quando o toggle muda, recarregar posicoes com `incluirZeradas` correspondente.
- Posicoes com quantidade `0` devem ser renderizadas como encerradas, sem permitir que o frontend apague historico localmente.

## Acessibilidade

- Componentes principais com labels acessiveis.
- Tabelas com cabecalhos claros.
- Mensagens de erro associadas aos campos.
