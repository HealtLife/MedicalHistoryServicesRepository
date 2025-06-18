package com.healthlife.u20221b471.main.medicalhistoryservice.application.services;

import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.MedicalNotesRequestDto;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper.MedicalNotesMapper;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.MedicalNotes;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.MedicalNotesRepository;
import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<MedicalNotesRequestDto> getMedicalNotesByDni(String dni) {
        List<MedicalNotes> medical = medicalNotesRepository.findByDni(dni);
        if (medical.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene alergias registradas.");
        }
        return medical.stream()
                .map(mapper::toDto)
                .toList(); // o .collect(Collectors.toList()) si estás usando Java 8
    }

    @Transactional
    public void updateMedicalNote(Long id, MedicalNotesRequestDto dto) {
        MedicalNotes existing = medicalNotesRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Nota médica con ID " + id + " no encontrada."));

        existing.setFecha_nota(dto.getFecha_nota().toString());
        existing.setNotes(dto.getNotes());
        existing.setAutor(dto.getAutor());
        existing.setDni(dto.getDni());

        medicalNotesRepository.save(existing);
    }


}
