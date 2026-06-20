import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-ativos-page',
  imports: [CardModule],
  template: `
    <p-card>
      <h1 class="text-lg font-semibold text-slate-950">Ativos</h1>
      <p class="mt-2 text-sm text-slate-600">Cadastro com validacao assincrona de ticker pela Brapi.</p>
    </p-card>
  `
})
export class AtivosPage {}
