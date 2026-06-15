package com.petcare.center.platform.contexts.clinicmanagement.interfaces.rest;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.Clinic;
import com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories.ClinicRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicsController {
    private final ClinicRepository clinicRepository;
    public ClinicsController(ClinicRepository clinicRepository) { this.clinicRepository = clinicRepository; }

    @GetMapping
    public List<Clinic> getClinics(@RequestParam(required = false) String district) {
        return district == null ? clinicRepository.findAll() : clinicRepository.findByDistrictIgnoreCase(district);
    }

    @GetMapping("/{clinicId}")
    public Clinic getClinicById(@PathVariable Long clinicId) {
        return clinicRepository.findById(clinicId).orElseThrow(() -> new ResourceNotFoundException("Clinic not found with id " + clinicId));
    }

    @PostMapping
    public Clinic createClinic(@RequestBody Clinic clinic) { return clinicRepository.save(clinic); }

    @PatchMapping("/{clinicId}")
    public Clinic patchClinic(@PathVariable Long clinicId, @RequestBody Clinic payload) {
        var clinic = getClinicById(clinicId);
        clinic.updateFrom(payload);
        return clinicRepository.save(clinic);
    }
}
