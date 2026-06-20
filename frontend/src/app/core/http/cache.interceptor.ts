import { HttpEvent, HttpInterceptorFn, HttpResponse } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';

const CACHE_TTL_MS = 60_000;
const cache = new Map<string, { expiresAt: number; response: HttpResponse<unknown> }>();

export const cacheInterceptor: HttpInterceptorFn = (request, next): Observable<HttpEvent<unknown>> => {
  if (request.method !== 'GET' || !isCacheable(request.url)) {
    return next(request);
  }

  const key = request.urlWithParams;
  const cached = cache.get(key);
  if (cached && cached.expiresAt > Date.now()) {
    return of(cached.response.clone());
  }

  return next(request).pipe(
    tap((event) => {
      if (event instanceof HttpResponse) {
        cache.set(key, {
          expiresAt: Date.now() + CACHE_TTL_MS,
          response: event.clone()
        });
      }
    })
  );
};

function isCacheable(url: string): boolean {
  return url.includes('/posicoes') || url.includes('/dashboard');
}
