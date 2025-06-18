package com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa;

import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.WeightHeightHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeigthHegthRepository extends JpaRepository<WeightHeightHistory,String> {
    List<WeightHeightHistory> findByDni(String dni);
}
