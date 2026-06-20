import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-eventos-page',
  imports: [CardModule],
  template: `
    <p-card>
      <h1 class="text-lg font-semibold text-slate-950">Eventos corporativos</h1>
      <p class="mt-2 text-sm text-slate-600">Eventos alteram quantidade e mantem o Custo Total inalterado.</p>
    </p-card>
  `
})
export class EventosPage {}
