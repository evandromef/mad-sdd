import { render, screen } from '@testing-library/angular';
import { provideRouter } from '@angular/router';
import { App } from './app';

describe('App', () => {
  it('should create the app', async () => {
    const { fixture } = await render(App, {
      providers: [provideRouter([])]
    });

    expect(fixture.componentInstance).toBeTruthy();
  });

  it('should render the application shell', async () => {
    await render(App, {
      providers: [provideRouter([])]
    });

    expect(screen.getByRole('link', { name: /mad início/i })).toBeTruthy();
    expect(screen.getByRole('navigation', { name: /navegação principal/i }).textContent).toContain(
      'Dashboard'
    );
  });
});
