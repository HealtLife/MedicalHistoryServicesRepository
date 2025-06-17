package com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper;

import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.PersonalInfoRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
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
}
