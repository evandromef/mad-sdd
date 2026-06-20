# ADR-001 - Usar Custo Total em vez de Preco Medio

## Status

Aceita.

## Decisao

O sistema controla o investimento por Custo Total acumulado, nao por preco medio.

## Contexto

O usuario informa valor total da operacao ja incluindo taxas. O objetivo e rastrear o capital alocado remanescente apos compras e vendas.

## Consequencias

- Compra soma `valor_total` ao Custo Total.
- Venda reduz Custo Total proporcionalmente a quantidade vendida.
- Preco medio pode ser exibido no futuro como derivado, mas nao deve orientar regras de negocio.

