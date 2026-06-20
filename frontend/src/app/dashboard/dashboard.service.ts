import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Dashboard, Posicao } from '../core/models/api.models';
import { ApiClient } from '../core/services/api-client.service';

@Injectable({ providedIn: 'root' })
export class DashboardService {
  constructor(private readonly api: ApiClient) {}

  dashboard(carteiraId?: number): Observable<Dashboard> {
    return this.api.get<Dashboard>('/dashboard', { carteiraId });
  }

  posicoesConsolidadas(incluirZeradas = false): Observable<Posicao[]> {
    return this.api.get<Posicao[]>('/posicoes/consolidado', { incluirZeradas });
  }
}
