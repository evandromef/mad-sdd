import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';

import { ErrorResponse } from '../core/models/api.models';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login-page',
  imports: [ButtonModule, CardModule, InputTextModule, PasswordModule, ReactiveFormsModule],
  template: `
    <div class="flex min-h-screen items-center justify-center bg-slate-100 px-4">
      <p-card styleClass="w-full max-w-sm">
        <form class="space-y-4" [formGroup]="form" (ngSubmit)="submit()">
          <div>
            <h1 class="text-xl font-semibold text-slate-950">MAD Investimentos</h1>
            <p class="mt-1 text-sm text-slate-600">Acesse sua carteira.</p>
          </div>

          <label class="block">
            <span class="mb-1 block text-sm font-medium text-slate-700">Email</span>
            <input pInputText class="w-full" type="email" formControlName="email" autocomplete="email" />
          </label>

          <label class="block">
            <span class="mb-1 block text-sm font-medium text-slate-700">Senha</span>
            <p-password styleClass="w-full" inputStyleClass="w-full" formControlName="senha" [feedback]="false" [toggleMask]="true" />
          </label>

          @if (error()) {
            <p class="text-sm text-red-700">{{ error() }}</p>
          }

          <button pButton type="submit" class="w-full" label="Entrar" [disabled]="form.invalid || loading()" [loading]="loading()"></button>
        </form>
      </p-card>
    </div>
  `
})
export class LoginPage {
  private readonly fb = inject(FormBuilder);
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);
  readonly form = this.fb.nonNullable.group({
    email: ['', [Validators.required, Validators.email]],
    senha: ['', [Validators.required, Validators.minLength(8)]]
  });

  submit(): void {
    if (this.form.invalid) {
      return;
    }
    this.loading.set(true);
    this.error.set(null);
    this.auth.login(this.form.getRawValue()).subscribe({
      next: () => void this.router.navigateByUrl('/dashboard'),
      error: (error: ErrorResponse) => {
        this.error.set(error.message);
        this.loading.set(false);
      }
    });
  }
}
