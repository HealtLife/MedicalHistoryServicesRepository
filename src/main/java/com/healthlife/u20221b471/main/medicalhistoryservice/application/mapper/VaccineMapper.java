package com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper;

import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.VaccineRequestDto;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Vaccine;
import org.springframework.stereotype.Component;


@Component
public class VaccineMapper {
    public Vaccine toEntity(VaccineRequestDto dto){
        return new Vaccine(
                dto.getDni(),
                dto.getVacuna(),
                dto.getFechaAplicacion(),
                dto.getDosis()
        );
    }

    public VaccineRequestDto toDto(Vaccine entity) {
        VaccineRequestDto dto = new VaccineRequestDto();
        dto.setDni(entity.getDni());
        dto.setVacuna(entity.getVacuna());
        dto.setFechaAplicacion(entity.getFechaAplicacion());
        dto.setDosis(entity.getDosis());

        return dto;
    }
}

/*
*     private String dni;
    private String vacuna;
    private LocalDate fechaAplicacion;
    private String dosis;

* */