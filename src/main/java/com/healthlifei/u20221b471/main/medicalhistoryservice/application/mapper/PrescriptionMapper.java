package com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper;

import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.PrescriptionRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Prescription;
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
}

//String dni, String prescripcion, LocalDate fechaReceta, String medico

