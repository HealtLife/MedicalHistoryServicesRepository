package com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa;

import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<personal_info, String> {
}
