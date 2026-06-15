package com.petcare.center.platform.contexts.clinicmanagement.interfaces.rest;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.ClinicService;
import com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories.ClinicServiceRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ClinicServicesController {
    private final ClinicServiceRepository clinicServiceRepository;
    public ClinicServicesController(ClinicServiceRepository clinicServiceRepository) { this.clinicServiceRepository = clinicServiceRepository; }
    @GetMapping
    public List<ClinicService> getServices(@RequestParam(required = false) Long clinicId) {
        return clinicId == null ? clinicServiceRepository.findAll() : clinicServiceRepository.findByClinicId(clinicId);
    }
    @GetMapping("/{serviceId}")
    public ClinicService getServiceById(@PathVariable Long serviceId) {
        return clinicServiceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + serviceId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClinicService createService(@RequestBody ClinicService service) { return clinicServiceRepository.save(service); }
    @PutMapping("/{serviceId}")
    public ClinicService updateService(@PathVariable Long serviceId, @RequestBody ClinicService payload) {
        var service = getServiceById(serviceId);
        service.updateFrom(payload);
        return clinicServiceRepository.save(service);
    }
    @DeleteMapping("/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable Long serviceId) {
        if (!clinicServiceRepository.existsById(serviceId)) throw new ResourceNotFoundException("Service not found with id " + serviceId);
        clinicServiceRepository.deleteById(serviceId);
    }
}
