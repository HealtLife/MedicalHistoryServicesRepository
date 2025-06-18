package com.healthlife.u20221b471.main.medicalhistoryservice.application.mapper;

import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.MedicalNotesRequestDto;
import com.healthlife.u20221b471.main.medicalhistoryservice.domain.model.aggregates.MedicalNotes;
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
