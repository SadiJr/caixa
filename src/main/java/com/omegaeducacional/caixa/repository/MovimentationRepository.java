package com.omegaeducacional.caixa.repository;

import com.omegaeducacional.caixa.domain.movimentation.Movimentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovimentationRepository extends JpaRepository<Movimentation, Long> {
    List<Movimentation> findByCashDeskId(Long id);

    @Query(nativeQuery = true, value = "select * from movimentation where date like concat(:filter, '%') and cash_desk_id = :cashDeskId")
    List<Movimentation> findByYearAndMonthAndCashDeskId(@Param("filter") String filter, @Param("cashDeskId") Long cashDeskId);
}
