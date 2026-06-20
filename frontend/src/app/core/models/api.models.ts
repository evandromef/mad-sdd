export type TipoAtivo = 'ACAO' | 'FII';
export type TipoOperacao = 'COMPRA' | 'VENDA';
export type TipoEventoCorporativo = 'SPLIT' | 'GRUPAMENTO' | 'BONIFICACAO';
export type TipoProvento = 'DIVIDENDO' | 'JCP' | 'RENDIMENTO' | 'AMORTIZACAO';

export interface ErrorResponse {
  code: string;
  message: string;
  details?: Record<string, unknown>;
}

export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  tokenType: 'Bearer' | string;
}

export interface LoginRequest {
  email: string;
  senha: string;
}

export interface Carteira {
  id: number;
  nome: string;
  descricao?: string | null;
  dataCriacao?: string;
}

export interface Ativo {
  id: number;
  ticker: string;
  nome: string;
  tipo: TipoAtivo;
  setor?: string | null;
  segmento?: string | null;
  ativo: boolean;
}

export interface Posicao {
  carteiraId: number;
  ativoId: number;
  ticker: string;
  tipo: TipoAtivo;
  quantidadeAtual: string;
  custoTotal: string;
  cotacaoAtual?: string | null;
  valorMercado?: string | null;
  pnlNaoRealizado?: string | null;
  percentualAlocacao?: string | null;
  cotacaoDesatualizada: boolean;
}

export interface Dashboard {
  patrimonioTotal?: string;
  pnlTotal?: string;
  maiorPosicaoTicker?: string | null;
  alocacaoPorClasse?: Array<{
    classe: string;
    percentual: string;
  }>;
}
