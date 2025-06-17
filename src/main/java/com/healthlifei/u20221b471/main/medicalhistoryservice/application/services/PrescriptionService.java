package com.healthlifei.u20221b471.main.medicalhistoryservice.application.services;

import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.PrescriptionRequestDto;

import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Prescription;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PrescriptionRepository;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper.PrescriptionMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final PrescriptionMapper mapper;





    public PrescriptionService(PrescriptionRepository prescriptionRepository, PersonalInfoRepository personalInfoRepository, PrescriptionMapper map) {
        this.prescriptionRepository = prescriptionRepository;
        this.personalInfoRepository = personalInfoRepository;
        this.mapper = map;
    }


    @Transactional
    public void savePrescription(PrescriptionRequestDto dto) {
        Optional<personal_info> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }

        Prescription prescription = mapper.toEntity(dto);
        prescriptionRepository.save(prescription);
    }

}

/*
*
* // Verifica si el DNI existe en la tabla personal_info
        Optional<personal_info> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }

        // Convertimos el DTO a la entidad
        MedicalNotes note = mapper.toEntity(dto);

        // Guarda la nota médica
        medicalNotesRepository.save(note);
*
* */
