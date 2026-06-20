import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

import { ErrorResponse } from '../models/api.models';

export const errorInterceptor: HttpInterceptorFn = (request, next) => {
  return next(request).pipe(
    catchError((error: HttpErrorResponse) => {
      const body = error.error as Partial<ErrorResponse> | undefined;
      const mapped: ErrorResponse = {
        code: body?.code ?? 'ERR-011',
        message: messageFor(body?.code),
        details: body?.details
      };
      return throwError(() => mapped);
    })
  );
};

function messageFor(code?: string): string {
  const messages: Record<string, string> = {
    'ERR-001': 'Autenticacao obrigatoria.',
    'ERR-002': 'Acesso negado ao recurso informado.',
    'ERR-003': 'Ticker invalido ou nao encontrado.',
    'ERR-004': 'Servico de cotacoes indisponivel.',
    'ERR-005': 'Ja existe um ativo com este ticker.',
    'ERR-006': 'Quantidade de venda maior que a posicao atual.',
    'ERR-007': 'Valor informado deve ser maior que zero.',
    'ERR-008': 'Nao e possivel excluir a ultima carteira.',
    'ERR-009': 'Carteira possui registros vinculados.',
    'ERR-010': 'Recurso nao encontrado.',
    'ERR-011': 'Dados de entrada invalidos.',
    'ERR-012': 'Email ja cadastrado.',
    'ERR-013': 'Alteracao invalida: o historico ficaria com quantidade negativa.'
  };
  return messages[code ?? ''] ?? 'Nao foi possivel concluir a operacao.';
}
