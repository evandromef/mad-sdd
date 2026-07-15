import { CurrencyPipe, PercentPipe } from '@angular/common';
import { Component } from '@angular/core';
import { TagModule } from 'primeng/tag';

type PositionSummary = {
  ticker: string;
  category: 'Ação' | 'FII';
  quantity: number;
  acquisitionCost: number;
  currentValue: number;
  allocation: number;
};

@Component({
  selector: 'app-dashboard-page',
  imports: [CurrencyPipe, PercentPipe, TagModule],
  templateUrl: './dashboard-page.html',
  styleUrl: './dashboard-page.css'
})
export class DashboardPage {
  protected readonly summaryCards = [
    { label: 'Custo de aquisição', value: 42840.36 },
    { label: 'Valor atual', value: 46192.11 },
    { label: 'P&L', value: 3351.75 }
  ];

  protected readonly positions: PositionSummary[] = [
    {
      ticker: 'PETR4',
      category: 'Ação',
      quantity: 300,
      acquisitionCost: 9680,
      currentValue: 10590,
      allocation: 0.2293
    },
    {
      ticker: 'MXRF11',
      category: 'FII',
      quantity: 620,
      acquisitionCost: 6450.36,
      currentValue: 6324,
      allocation: 0.1369
    },
    {
      ticker: 'ITSA4',
      category: 'Ação',
      quantity: 900,
      acquisitionCost: 9240,
      currentValue: 10080,
      allocation: 0.2182
    }
  ];
}
