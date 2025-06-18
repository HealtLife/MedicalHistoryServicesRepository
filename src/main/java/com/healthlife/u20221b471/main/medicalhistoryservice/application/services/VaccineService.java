package com.healthlife.u20221b471.main.medicalhistoryservice.application.services;


import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.Vaccinerepository;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.VaccineRequestDto;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper.VaccineMapper;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Vaccine;
import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    private final PersonalInfoRepository personalInfoRepository;
    private final Vaccinerepository vaccinerepository;
    private final VaccineMapper mapper;


    public VaccineService(PersonalInfoRepository personalInfoRepository, Vaccinerepository vaccinerepository, VaccineMapper mapper) {
        this.personalInfoRepository = personalInfoRepository;
        this.vaccinerepository = vaccinerepository;
        this.mapper = mapper;
    }

    @Transactional
    public void saveVaccine(VaccineRequestDto dto){
        Optional<personal_info> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }

        Vaccine entity = mapper.toEntity(dto);
        vaccinerepository.save(entity);

    }
    public List<VaccineRequestDto> getVaccineByDni(String dni) {
        List<Vaccine> vaccines = vaccinerepository.findByDni(dni);
        if (vaccines.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene alergias registradas.");
        }
        return vaccines.stream()
                .map(mapper::toDto)
                .toList(); // o .collect(Collectors.toList()) si estás usando Java 8
    }

}
