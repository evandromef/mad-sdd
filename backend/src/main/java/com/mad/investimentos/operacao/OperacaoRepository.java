package com.mad.investimentos.operacao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

    List<Operacao> findByCarteiraIdAndAtivoId(Long carteiraId, Long ativoId);
}
