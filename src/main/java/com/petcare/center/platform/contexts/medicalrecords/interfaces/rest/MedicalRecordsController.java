package com.petcare.center.platform.contexts.medicalrecords.interfaces.rest;

import com.petcare.center.platform.contexts.medicalrecords.domain.model.aggregates.MedicalRecord;
import com.petcare.center.platform.contexts.medicalrecords.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medical-records")
public class MedicalRecordsController {
    private final MedicalRecordRepository medicalRecordRepository;
    public MedicalRecordsController(MedicalRecordRepository medicalRecordRepository) { this.medicalRecordRepository = medicalRecordRepository; }
    @GetMapping
    public List<MedicalRecord> getRecords(@RequestParam(required = false) Long petId) {
        return petId == null ? medicalRecordRepository.findAll() : medicalRecordRepository.findByPetId(petId);
    }
    @GetMapping("/{recordId}")
    public MedicalRecord getRecordById(@PathVariable Long recordId) {
        return medicalRecordRepository.findById(recordId).orElseThrow(() -> new ResourceNotFoundException("Medical record not found with id " + recordId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecord createRecord(@RequestBody MedicalRecord record) { return medicalRecordRepository.save(record); }
}
