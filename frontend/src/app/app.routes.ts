import { Routes } from '@angular/router';

import { authGuard } from './auth/auth.guard';
import { LoginPage } from './auth/login.page';
import { AppLayout } from './core/layout/app.layout';
import { AtivosPage } from './ativos/ativos.page';
import { CarteirasPage } from './carteiras/carteiras.page';
import { DashboardPage } from './dashboard/dashboard.page';
import { EventosPage } from './eventos/eventos.page';
import { OperacoesPage } from './operacoes/operacoes.page';
import { ProventosPage } from './proventos/proventos.page';

export const routes: Routes = [
  { path: 'login', component: LoginPage },
  {
    path: '',
    component: AppLayout,
    canActivate: [authGuard],
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'dashboard' },
      { path: 'dashboard', component: DashboardPage },
      { path: 'carteiras', component: CarteirasPage },
      { path: 'ativos', component: AtivosPage },
      { path: 'operacoes', component: OperacoesPage },
      { path: 'eventos', component: EventosPage },
      { path: 'proventos', component: ProventosPage }
    ]
  },
  { path: '**', redirectTo: 'dashboard' }
];
