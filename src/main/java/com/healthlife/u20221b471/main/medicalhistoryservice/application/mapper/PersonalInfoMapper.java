package com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper;

import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.PersonalInfoRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PersonalInfoMapper {
    public personal_info toEntity(PersonalInfoRequestDto dto) {
        return new personal_info(
                dto.getDni(),
                dto.getFechaNacimiento(),
                dto.getGenero(),
                dto.getTipoCuerpo(),
                dto.getImc()
        );
    }
    public PersonalInfoRequestDto toDto(personal_info entity) {
        PersonalInfoRequestDto dto = new PersonalInfoRequestDto();
        dto.setDni(entity.getDni());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setGenero(entity.getGenero());
        dto.setTipoCuerpo(entity.getTipoCuerpo());
        dto.setImc(entity.getImc());
        return dto;
    }

}

