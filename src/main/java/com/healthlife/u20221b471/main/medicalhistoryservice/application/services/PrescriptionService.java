package com.healthlife.u20221b471.main.medicalhistoryservice.application.services;

import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PrescriptionRepository;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.PrescriptionRequestDto;

import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Prescription;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper.PrescriptionMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<PrescriptionRequestDto> getPrescriptionByDni(String dni) {
        List<Prescription> prescription = prescriptionRepository.findByDni(dni);
        if (prescription.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene alergias registradas.");
        }
        return prescription.stream()
                .map(mapper::toDto)
                .toList(); // o .collect(Collectors.toList()) si estás usando Java 8
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
