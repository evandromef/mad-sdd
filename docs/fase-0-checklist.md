# Fase 0 Checklist

## Artefatos Criados

| ID | Artefato | Status |
|---|---|---|
| DOC-01 | `AGENTS.md` | Feito |
| DOC-02 | `docs/schema.sql` | Feito |
| DOC-03 | `docs/api/openapi.yaml` | Feito, primeira versao |
| DOC-04 | `docs/business-rules.md` | Feito |
| DOC-05 | `docs/tests/test-scenarios.md` | Feito |
| DOC-06 | `docs/ui-spec.md` | Feito |
| DOC-07 | `docker-compose.yml` + `.env.example` | Feito, ajustado para Dockerfiles backend/frontend |
| DOC-08 | `backend/src/main/resources/db/migration/` | Feito |
| DOC-09 | `docs/adr/` | Feito, ADR-001 a ADR-008 |
| DOC-10 | `docs/auth-spec.md` | Feito |
| DOC-11 | `docs/brapi-integration.md` | Feito |
| DOC-12 | `.github/workflows/ci.yml` | Feito, jobs condicionais |
| DOC-14 | `docs/domain/domain-model.md` | Feito |
| DOC-15 | `docs/domain/state-transitions.md` | Feito |
| DOC-16 | `docs/sequences/` | Feito, 3 sequencias |
| DOC-17 | `docs/api/error-catalog.md` | Feito |
| DOC-18 | `docs/tests/acceptance-tests.feature` | Feito |
| DOC-19 | `docs/frontend/frontend-architecture.md` | Feito |
| DOC-20 | `docs/backend/backend-architecture.md` | Feito |

## Pendencias Tecnicas da Fase 0

- Criar scaffold Angular em `frontend/`: feito e validado com `npm run build` e `npm test`.
- Criar scaffold Spring Boot em `backend/`: feito e validado com `mvn test`.
- Ajustar `docker-compose.yml` apos Dockerfiles reais: feito para backend e frontend.
- Rodar validacao OpenAPI com ferramenta dedicada quando dependencias estiverem disponiveis.
- Configurar repositorio remoto GitHub, Vercel, Render e Supabase.
