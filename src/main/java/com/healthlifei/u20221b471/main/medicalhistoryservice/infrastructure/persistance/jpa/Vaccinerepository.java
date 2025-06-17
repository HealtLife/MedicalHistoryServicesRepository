package com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa;

import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Prescription;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Vaccinerepository extends JpaRepository<Vaccine, String> {
    List<Vaccine> findByDni(String dni);
}
