import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Posicao } from '../core/models/api.models';
import { ApiClient } from '../core/services/api-client.service';

@Injectable({ providedIn: 'root' })
export class PosicoesService {
  constructor(private readonly api: ApiClient) {}

  listar(carteiraId: number, incluirZeradas = false): Observable<Posicao[]> {
    return this.api.get<Posicao[]>(`/carteiras/${carteiraId}/posicoes`, { incluirZeradas });
  }
}
