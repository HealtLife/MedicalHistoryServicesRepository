package com.healthlifei.u20221b471.main.medicalhistoryservice.application.services;


import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.AllergyRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper.AllergyMapper;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Allergy;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.MedicalNotes;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.AllergyRepository;
import com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AllergyService {
    private final PersonalInfoRepository personalInfoRepository;
    private final AllergyRepository allergyRepository;
    private final AllergyMapper mapper;
    public AllergyService(PersonalInfoRepository personalInfoRepository, AllergyRepository allergyRepository, AllergyMapper mapper) {
        this.personalInfoRepository = personalInfoRepository;
        this.allergyRepository = allergyRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void saveAllergy(AllergyRequestDto dto) {
        // Verifica si el DNI existe en la tabla personal_info
        Optional<personal_info> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }
        // Convertimos el DTO a la entidad
        Allergy alergias = mapper.toEntity(dto);

        // Guarda la nota médica
        allergyRepository.save(alergias);
    }
}
