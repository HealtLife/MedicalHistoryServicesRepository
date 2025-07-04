package com.healthlife.u20221b471.main.medicalhistoryservice.application.services;


import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.AllergyRepository;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.AllergyRequestDto;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper.AllergyMapper;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Allergy;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<AllergyRequestDto> getAllergiesByDni(String dni) {
        List<Allergy> allergies = allergyRepository.findByDni(dni);
        if (allergies.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene alergias registradas.");
        }
        return allergies.stream()
                .map(mapper::toDto)
                .toList(); // o .collect(Collectors.toList()) si estás usando Java 8
    }
    @Transactional
    public void updateAllergy(Long id, AllergyRequestDto dto) {
        Allergy existing = allergyRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Alergia con ID " + id + " no encontrada."));

        existing.setDni(dto.getDni());
        existing.setAlergia(dto.getAlergia());
        existing.setReaccion(dto.getReaccion());

        allergyRepository.save(existing);
    }

}
