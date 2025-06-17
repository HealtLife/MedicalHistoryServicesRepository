package com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper;

import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.PersonalInfoRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.PrescriptionRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Prescription;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.personal_info;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapper {
    public Prescription toEntity(PrescriptionRequestDto dto){
        return new Prescription(
                dto.getDni(),
                dto.getPrescripcion(),
                dto.getFecha_receta(),
                dto.getMedico()
        );
    }

    public PrescriptionRequestDto toDto(Prescription entity) {
        PrescriptionRequestDto dto = new PrescriptionRequestDto();
        dto.setDni(entity.getDni());
        dto.setPrescripcion(entity.getPrescripcion());
        dto.setFecha_receta(entity.getFechaReceta());
        dto.setMedico(entity.getMedico());
        return dto;
    }

}

//String dni, String prescripcion, LocalDate fechaReceta, String medico

