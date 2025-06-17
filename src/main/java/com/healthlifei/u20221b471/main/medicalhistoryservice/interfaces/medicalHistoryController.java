package com.healthlifei.u20221b471.main.medicalhistoryservice.interfaces;

import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.AllergyRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.MedicalNotesRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.PersonalInfoRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto.PrescriptionRequestDto;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.services.AllergyService;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.services.MedicalNotesService;
import com.healthlifei.u20221b471.main.medicalhistoryservice.application.services.PersonalInfoService;
import com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates.MedicalNotes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/medical-history", produces = APPLICATION_JSON_VALUE)
public class medicalHistoryController {
    private final PersonalInfoService personalInfoService;
    private final MedicalNotesService medicalNotesService;
    private final AllergyService allergyService;
    private final com.healthlifei.u20221b471.main.medicalhistoryservice.application.services.PrescriptionService prescriptionService;


    public medicalHistoryController(PersonalInfoService personalInfoService, MedicalNotesService medicalNotesService, AllergyService allergyService, com.healthlifei.u20221b471.main.medicalhistoryservice.application.services.PrescriptionService prescriptionService) {
        this.personalInfoService = personalInfoService;
        this.medicalNotesService = medicalNotesService;
        this.allergyService = allergyService;
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/personal-info")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePersonalInfo(@RequestBody PersonalInfoRequestDto dto) {
        personalInfoService.savePersonalInfo(dto);
    }

    @PutMapping("/personal-info/{dni}")
    public void updatePersonalInfo(@PathVariable String dni, @RequestBody PersonalInfoRequestDto dto) {
        personalInfoService.updatePersonalInfo(dni, dto);
    }


    @PostMapping("/medical-note")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMedicalNote(@RequestBody MedicalNotesRequestDto dto) {
        medicalNotesService.saveMedicalNote(dto);
    }
    /*@PostMapping("/medical-note")
    public ResponseEntity<String> addNote(@RequestBody MedicalNotesRequestDto request) {
        MedicalNotes note = new MedicalNotes(request.getDni(), request.getNotes(), request.getAutor(),request.getFecha_nota());
        medicalNotesService.saveMedicalNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body("Nota médica agregada.");
    }*/

    @PostMapping("/allergies")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAllergy(@RequestBody AllergyRequestDto dto) {
        allergyService.saveAllergy(dto);
    }

    @PostMapping("/prescription")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePrescription(@RequestBody PrescriptionRequestDto dto) {
        prescriptionService.savePrescription(dto);
    }
}
