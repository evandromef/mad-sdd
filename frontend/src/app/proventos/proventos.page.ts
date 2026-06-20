import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-proventos-page',
  imports: [CardModule],
  template: `
    <p-card>
      <h1 class="text-lg font-semibold text-slate-950">Proventos</h1>
      <p class="mt-2 text-sm text-slate-600">Registro manual do valor total recebido.</p>
    </p-card>
  `
})
export class ProventosPage {}
