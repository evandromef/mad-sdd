import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-dashboard-page',
  imports: [CardModule, TableModule, TagModule],
  template: `
    <section class="space-y-4">
      <div class="grid gap-3 sm:grid-cols-2 xl:grid-cols-4">
        @for (card of cards; track card.label) {
          <p-card>
            <p class="text-sm text-slate-500">{{ card.label }}</p>
            <p class="mt-2 text-2xl font-semibold text-slate-950">{{ card.value }}</p>
          </p-card>
        }
      </div>

      <p-card>
        <div class="mb-3 flex items-center justify-between gap-3">
          <h1 class="text-lg font-semibold text-slate-950">Posicoes consolidadas</h1>
          <p-tag severity="info" value="cache 60s" />
        </div>
        <p-table [value]="[]" [tableStyle]="{ 'min-width': '720px' }">
          <ng-template pTemplate="header">
            <tr>
              <th>Ticker</th>
              <th>Classe</th>
              <th>Quantidade</th>
              <th>Custo Total</th>
              <th>Valor mercado</th>
              <th>P&L</th>
            </tr>
          </ng-template>
          <ng-template pTemplate="emptymessage">
            <tr>
              <td colspan="6" class="text-center text-slate-500">Nenhuma posicao para exibir.</td>
            </tr>
          </ng-template>
        </p-table>
      </p-card>
    </section>
  `
})
export class DashboardPage {
  readonly cards = [
    { label: 'Patrimonio total', value: 'R$ 0,00' },
    { label: 'P&L total', value: 'R$ 0,00' },
    { label: 'Proventos do mes', value: 'R$ 0,00' },
    { label: 'Maior posicao', value: '-' }
  ];
}
