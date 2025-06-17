package com.healthlifei.u20221b471.main.medicalhistoryservice.application.services;

import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.MedicalNotesRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper.MedicalNotesMapper;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.MedicalNotes;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.MedicalNotesRepository;
import com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalNotesService {
    private final MedicalNotesRepository medicalNotesRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final MedicalNotesMapper mapper;

    public MedicalNotesService(MedicalNotesRepository medicalNotesRepository, PersonalInfoRepository personalInfoRepository, MedicalNotesMapper mapper) {
        this.medicalNotesRepository = medicalNotesRepository;
        this.personalInfoRepository = personalInfoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void saveMedicalNote(MedicalNotesRequestDto dto) {
        // Verifica si el DNI existe en la tabla personal_info
        Optional<personal_info> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }

        // Convertimos el DTO a la entidad
        MedicalNotes note = mapper.toEntity(dto);

        // Guarda la nota médica
        medicalNotesRepository.save(note);
    }

}
