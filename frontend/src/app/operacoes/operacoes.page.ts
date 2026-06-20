import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-operacoes-page',
  imports: [CardModule],
  template: `
    <p-card>
      <h1 class="text-lg font-semibold text-slate-950">Operacoes</h1>
      <p class="mt-2 text-sm text-slate-600">Compras e vendas usam Custo Total conforme RN-01 e RN-02.</p>
    </p-card>
  `
})
export class OperacoesPage {}
