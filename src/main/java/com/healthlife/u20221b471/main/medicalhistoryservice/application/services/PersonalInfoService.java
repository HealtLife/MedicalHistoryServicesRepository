package com.healthlife.u20221b471.main.medicalhistoryservice.application.services;

import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.PersonalInfoRequestDto;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper.PersonalInfoMapper;
import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalInfoService {
    private final PersonalInfoRepository repository;
    private final PersonalInfoMapper mapper;
    public PersonalInfoService(PersonalInfoRepository repository, PersonalInfoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public void savePersonalInfo(PersonalInfoRequestDto dto) {
        Optional<personal_info> existing = repository.findById(dto.getDni());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("El paciente con DNI " + dto.getDni() + " ya existe.");
        }
        personal_info entity = mapper.toEntity(dto);
        repository.save(entity);
    }


    @Transactional
    public void updatePersonalInfo(String dni, PersonalInfoRequestDto dto) {
        personal_info existing = repository.findById(dni)
                .orElseThrow(() -> new EntityNotFoundException("Paciente con DNI " + dni + " no encontrado"));

        // Actualiza campos específicos
        existing.setFechaNacimiento(dto.getFechaNacimiento());
        existing.setGenero(dto.getGenero());
        existing.setTipoCuerpo(dto.getTipoCuerpo());
        existing.setImc(dto.getImc());

        repository.save(existing);
    }

    public PersonalInfoRequestDto getPersonalInfoByDni(String dni) {
        personal_info entity = repository.findById(dni)
                .orElseThrow(() -> new EntityNotFoundException("Paciente con DNI " + dni + " no encontrado"));
        return mapper.toDto(entity);
    }



}
