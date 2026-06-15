package com.petcare.center.platform.contexts.clinicmanagement.interfaces.rest;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.Veterinarian;
import com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories.VeterinarianRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/veterinarians")
public class VeterinariansController {
    private final VeterinarianRepository veterinarianRepository;
    public VeterinariansController(VeterinarianRepository veterinarianRepository) { this.veterinarianRepository = veterinarianRepository; }
    @GetMapping
    public List<Veterinarian> getVeterinarians(@RequestParam(required = false) Long clinicId) {
        return clinicId == null ? veterinarianRepository.findAll() : veterinarianRepository.findByClinicId(clinicId);
    }
    @GetMapping("/{veterinarianId}")
    public Veterinarian getVeterinarianById(@PathVariable Long veterinarianId) {
        return veterinarianRepository.findById(veterinarianId).orElseThrow(() -> new ResourceNotFoundException("Veterinarian not found with id " + veterinarianId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veterinarian createVeterinarian(@RequestBody Veterinarian veterinarian) { return veterinarianRepository.save(veterinarian); }
    @PutMapping("/{veterinarianId}")
    public Veterinarian updateVeterinarian(@PathVariable Long veterinarianId, @RequestBody Veterinarian payload) {
        var veterinarian = getVeterinarianById(veterinarianId);
        veterinarian.updateFrom(payload);
        return veterinarianRepository.save(veterinarian);
    }
    @DeleteMapping("/{veterinarianId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVeterinarian(@PathVariable Long veterinarianId) {
        if (!veterinarianRepository.existsById(veterinarianId)) throw new ResourceNotFoundException("Veterinarian not found with id " + veterinarianId);
        veterinarianRepository.deleteById(veterinarianId);
    }
}
