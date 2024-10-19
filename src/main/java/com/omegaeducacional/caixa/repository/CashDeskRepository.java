package com.omegaeducacional.caixa.repository;

import com.omegaeducacional.caixa.domain.cash.CashDesk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashDeskRepository extends JpaRepository <CashDesk, Long> {
    CashDesk findByDescription(String description);

    List<CashDesk> findByBalance(Double doubleValue);
}
