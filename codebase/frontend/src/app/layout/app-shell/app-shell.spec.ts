import { provideRouter } from '@angular/router';
import { render, screen } from '@testing-library/angular';
import { AppShell } from './app-shell';

describe('AppShell', () => {
  it('should render the brand and primary navigation', async () => {
    await render(AppShell, {
      providers: [provideRouter([])]
    });

    const brandLink = screen.getByRole('link', { name: /mad início/i });
    const navigation = screen.getByRole('navigation', { name: /navegação principal/i });

    expect(brandLink).toBeInstanceOf(HTMLAnchorElement);
    expect(brandLink.getAttribute('href')).toBe('/');

    expect(navigation).toBeInstanceOf(HTMLElement);
    expect(navigation.textContent?.toLowerCase()).toContain('dashboard');
  });

  it('should render the primary operation action', async () => {
    await render(AppShell, {
      providers: [provideRouter([])]
    });

    expect(screen.getByRole('button', { name: /nova operação/i })).toBeInstanceOf(
      HTMLButtonElement
    );
  });
});
