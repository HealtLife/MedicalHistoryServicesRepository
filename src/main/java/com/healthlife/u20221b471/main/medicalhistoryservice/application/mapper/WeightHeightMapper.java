package com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper;

import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.WeigthHegthDto;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.WeightHeightHistory;
import org.springframework.stereotype.Component;

@Component
public class WeightHeightMapper {
    public WeightHeightHistory toEntity(WeigthHegthDto dto){
        return new WeightHeightHistory(
                dto.getDni(),
                dto.getPeso(),
                dto.getAltura(),
                dto.getFechaRegistro()
        );
    }
    public WeigthHegthDto toDto(WeightHeightHistory entity) {
        WeigthHegthDto dto = new WeigthHegthDto();
        dto.setDni(entity.getDni());
        dto.setPeso(entity.getPeso());
        dto.setAltura(entity.getAltura());
        dto.setFechaRegistro(entity.getFechaRegistro());
        return dto;
    }
}

/*
*
*
*     private String dni;

    private Double peso;

    private Double altura;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;*/