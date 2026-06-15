package com.petcare.center.platform.contexts.clinicmanagement.interfaces.rest;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.Patient;
import com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientsController {
    private final PatientRepository patientRepository;
    public PatientsController(PatientRepository patientRepository) { this.patientRepository = patientRepository; }
    @GetMapping
    public List<Patient> getPatients(@RequestParam(required = false) Long clinicId) {
        return clinicId == null ? patientRepository.findAll() : patientRepository.findByClinicId(clinicId);
    }
    @GetMapping("/{petId}")
    public Patient getPatientByPetId(@PathVariable Long petId) {
        return patientRepository.findByPetId(petId).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with pet id " + petId));
    }
}
