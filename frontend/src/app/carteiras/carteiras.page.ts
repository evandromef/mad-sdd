import { Component, signal } from '@angular/core';
import { CardModule } from 'primeng/card';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-carteiras-page',
  imports: [CardModule, TableModule],
  template: `
    <p-card>
      <div class="mb-4 flex items-center justify-between gap-3">
        <h1 class="text-lg font-semibold text-slate-950">Carteira e posicoes</h1>
        <label class="flex items-center gap-2 text-sm text-slate-700">
          <input type="checkbox" [checked]="incluirZeradas()" (change)="incluirZeradas.set(!incluirZeradas())" />
          Mostrar zerados
        </label>
      </div>

      <p-table [value]="[]" [tableStyle]="{ 'min-width': '760px' }">
        <ng-template pTemplate="header">
          <tr>
            <th>Ticker</th>
            <th>Nome</th>
            <th>Classe</th>
            <th>Quantidade</th>
            <th>Custo Total</th>
            <th>Cotacao</th>
            <th>P&L</th>
          </tr>
        </ng-template>
        <ng-template pTemplate="emptymessage">
          <tr>
            <td colspan="7" class="text-center text-slate-500">Nenhuma posicao cadastrada.</td>
          </tr>
        </ng-template>
      </p-table>
    </p-card>
  `
})
export class CarteirasPage {
  readonly incluirZeradas = signal(false);
}
