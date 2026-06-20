import { Injectable, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';

import { AuthResponse, LoginRequest } from '../core/models/api.models';
import { ApiClient } from '../core/services/api-client.service';

const ACCESS_TOKEN_KEY = 'mad.accessToken';
const REFRESH_TOKEN_KEY = 'mad.refreshToken';

@Injectable({ providedIn: 'root' })
export class AuthService {
  readonly authenticated = signal(Boolean(this.accessToken));

  constructor(private readonly api: ApiClient) {}

  get accessToken(): string | null {
    return localStorage.getItem(ACCESS_TOKEN_KEY);
  }

  login(request: LoginRequest): Observable<AuthResponse> {
    return this.api.post<AuthResponse>('/auth/login', request).pipe(
      tap((response) => this.storeTokens(response))
    );
  }

  logout(): void {
    localStorage.removeItem(ACCESS_TOKEN_KEY);
    localStorage.removeItem(REFRESH_TOKEN_KEY);
    this.authenticated.set(false);
  }

  private storeTokens(response: AuthResponse): void {
    localStorage.setItem(ACCESS_TOKEN_KEY, response.accessToken);
    localStorage.setItem(REFRESH_TOKEN_KEY, response.refreshToken);
    this.authenticated.set(true);
  }
}
