package com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa;


import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {

    List<Prescription> findByDni(String dni);
}
