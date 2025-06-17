package com.healthlifei.u20221b471.main.medicalhistoryservice.application.services;

import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.VaccineRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.WeigthHegthDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper.WeightHeightMapper;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Vaccine;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.WeightHeightHistory;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import com.healthlifei.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.WeigthHegthRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeigthHegthService {
    private final PersonalInfoRepository personalInfoRepository;
    private final WeigthHegthRepository weigthHegthRepository;
    private final WeightHeightMapper mapper;

    public WeigthHegthService(PersonalInfoRepository personalInfoRepository, WeigthHegthRepository weigthHegthRepository, WeightHeightMapper mapper) {
        this.personalInfoRepository = personalInfoRepository;
        this.weigthHegthRepository = weigthHegthRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void saveWeightHeight(WeigthHegthDto dto){
        Optional<personal_info> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }

        WeightHeightHistory entity = mapper.toEntity(dto);
        weigthHegthRepository.save(entity);
    }

    public List<WeigthHegthDto> getWeightHeightByDni(String dni) {
        List<WeightHeightHistory> weightheight = weigthHegthRepository.findByDni(dni);
        if (weightheight.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene alergias registradas.");
        }
        return weightheight.stream()
                .map(mapper::toDto)
                .toList(); // o .collect(Collectors.toList()) si estás usando Java 8
    }

}
