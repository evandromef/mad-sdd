package com.mad.investimentos.evento;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoCorporativoRepository extends JpaRepository<EventoCorporativo, Long> {

    List<EventoCorporativo> findByCarteiraIdAndAtivoId(Long carteiraId, Long ativoId);
}
