import { registerLocaleData } from '@angular/common';
import localePtBr from '@angular/common/locales/pt';
import { render, screen, within } from '@testing-library/angular';
import { DashboardPage } from './dashboard-page';

registerLocaleData(localePtBr);

describe('DashboardPage', () => {
  it('should render positions as a semantic table', async () => {
    await render(DashboardPage);

    const table = screen.getByRole('table', { name: /posição por ativo/i });

    expect(within(table).getByRole('columnheader', { name: 'Ativo' })).toBeTruthy();
    expect(within(table).getByRole('columnheader', { name: 'Custo total' })).toBeTruthy();
    expect(within(table).getByRole('rowheader', { name: 'PETR4' })).toBeTruthy();
  });
});
