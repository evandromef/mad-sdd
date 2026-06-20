import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { ButtonModule } from 'primeng/button';

import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-layout',
  imports: [ButtonModule, RouterLink, RouterLinkActive, RouterOutlet],
  template: `
    <div class="min-h-screen">
      <header class="sticky top-0 z-10 border-b border-slate-200 bg-white">
        <div class="mx-auto flex h-14 max-w-7xl items-center justify-between px-4">
          <a routerLink="/dashboard" class="text-base font-semibold text-slate-950">MAD Investimentos</a>
          <button pButton type="button" size="small" severity="secondary" icon="pi pi-sign-out" label="Sair" (click)="auth.logout()"></button>
        </div>
      </header>

      <div class="mx-auto grid max-w-7xl gap-0 px-4 py-4 md:grid-cols-[220px_1fr] md:gap-6">
        <nav class="mb-4 flex gap-2 overflow-x-auto border-b border-slate-200 pb-3 md:mb-0 md:block md:border-b-0 md:pb-0">
          @for (item of items; track item.path) {
            <a
              [routerLink]="item.path"
              routerLinkActive="bg-slate-900 text-white"
              class="mb-1 inline-flex h-10 min-w-max items-center gap-2 rounded px-3 text-sm text-slate-700 hover:bg-slate-100 md:flex"
            >
              <i [class]="item.icon"></i>
              <span>{{ item.label }}</span>
            </a>
          }
        </nav>

        <main>
          <router-outlet />
        </main>
      </div>
    </div>
  `
})
export class AppLayout {
  readonly items = [
    { path: '/dashboard', label: 'Dashboard', icon: 'pi pi-chart-line' },
    { path: '/carteiras', label: 'Carteiras', icon: 'pi pi-briefcase' },
    { path: '/ativos', label: 'Ativos', icon: 'pi pi-tags' },
    { path: '/operacoes', label: 'Operacoes', icon: 'pi pi-arrow-right-arrow-left' },
    { path: '/eventos', label: 'Eventos', icon: 'pi pi-sitemap' },
    { path: '/proventos', label: 'Proventos', icon: 'pi pi-wallet' }
  ];

  constructor(readonly auth: AuthService) {}
}
