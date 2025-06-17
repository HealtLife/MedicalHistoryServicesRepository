package com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper;


import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.AllergyRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Allergy;
import org.springframework.stereotype.Component;

@Component
public class AllergyMapper {
    public Allergy toEntity(AllergyRequestDto dto){
        return new Allergy(
                dto.getDni(),
                dto.getAlergia(),
                dto.getReaccion()
        );
    }

}
