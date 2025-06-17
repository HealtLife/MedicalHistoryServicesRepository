package com.healthlifei.u20221b471.main.medicalhistoryservice.application.mapper;

import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.AllergyRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.MedicalNotesRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.Allergy;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.MedicalNotes;
import org.springframework.stereotype.Component;

@Component
public class MedicalNotesMapper {
    public MedicalNotes toEntity(MedicalNotesRequestDto dto) {
        return new MedicalNotes(
                dto.getDni(),
                dto.getNotes(),
                dto.getAutor(),
                dto.getFecha_nota()


        );
    }

    public MedicalNotesRequestDto toDto(MedicalNotes entity) {
        MedicalNotesRequestDto dto = new MedicalNotesRequestDto();
        dto.setDni(entity.getDni());
        dto.setNotes(entity.getNotes());
        dto.setAutor(entity.getAutor());
        dto.getFecha_nota();
        return dto;
    }

}
