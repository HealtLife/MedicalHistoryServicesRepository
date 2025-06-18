package com.healthlife.u20221b471.main.medicalhistoryservice.interfaces;

import com.healthlife.u20221b471.main.medicalhistoryservice.application.dto.*;
import com.healthlife.u20221b471.main.medicalhistoryservice.application.services.*;
import com.healthlife.u20221b471.main.medicalhistoryservice.infrastructure.persistance.jpa.Vaccinerepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/medical-history", produces = APPLICATION_JSON_VALUE)
public class medicalHistoryController {
    private final PersonalInfoService personalInfoService;
    private final MedicalNotesService medicalNotesService;
    private final AllergyService allergyService;
    private final PrescriptionService prescriptionService;
    private final Vaccinerepository vaccinerepository;
    private final VaccineService vaccineService;
    private final WeigthHegthService weigthHegthService;


    public medicalHistoryController(PersonalInfoService personalInfoService, MedicalNotesService medicalNotesService, AllergyService allergyService, PrescriptionService prescriptionService, Vaccinerepository vaccinerepository, VaccineService vaccineService, WeigthHegthService weigthHegthService) {
        this.personalInfoService = personalInfoService;
        this.medicalNotesService = medicalNotesService;
        this.allergyService = allergyService;
        this.prescriptionService = prescriptionService;
        this.vaccinerepository = vaccinerepository;
        this.vaccineService = vaccineService;
        this.weigthHegthService = weigthHegthService;
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

    @GetMapping("/personal-info/{dni}")
    public ResponseEntity<PersonalInfoRequestDto> getPersonalInfo(@PathVariable String dni) {
        PersonalInfoRequestDto dto = personalInfoService.getPersonalInfoByDni(dni);
        return ResponseEntity.ok(dto);
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

    @GetMapping("/allergies/{dni}")
    public ResponseEntity<List<AllergyRequestDto>> getAllergies(@PathVariable String dni) {
        List<AllergyRequestDto> dtos = allergyService.getAllergiesByDni(dni);
        return ResponseEntity.ok(dtos);
    }



    @PostMapping("/prescription")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePrescription(@RequestBody PrescriptionRequestDto dto) {
        prescriptionService.savePrescription(dto);
    }
    @GetMapping("/prescription/{dni}")
    public ResponseEntity<List<PrescriptionRequestDto>> getPrescriptions(@PathVariable String dni) {
        List<PrescriptionRequestDto> dtos = prescriptionService.getPrescriptionByDni(dni);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/vaccine")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVaccine(@RequestBody VaccineRequestDto dto) {
        vaccineService.saveVaccine(dto);
    }
    @GetMapping("/vaccine/{dni}")
    public ResponseEntity<List<VaccineRequestDto>> getVaccines(@PathVariable String dni) {
        List<VaccineRequestDto> dtos = vaccineService.getVaccineByDni(dni);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/weightheight")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveWeightHeight(@RequestBody WeigthHegthDto dto) {
        weigthHegthService.saveWeightHeight(dto);
    }
    @GetMapping("/weightheight/{dni}")
    public ResponseEntity<List<WeigthHegthDto>> getWeightHeight(@PathVariable String dni) {
        List<WeigthHegthDto> dtos = weigthHegthService.getWeightHeightByDni(dni);
        return ResponseEntity.ok(dtos);
    }




}
