package com.petcare.center.platform.contexts.mobile.interfaces.rest;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileAppointment;
import com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories.MobileAppointmentRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mobile-appointments")
public class MobileAppointmentsController {
    private final MobileAppointmentRepository repository;
    public MobileAppointmentsController(MobileAppointmentRepository repository) { this.repository = repository; }

    @GetMapping
    public List<MobileAppointment> getAll(@RequestParam(required = false) Long mobileId) {
        return mobileId == null ? repository.findAll() : repository.findByMobileId(mobileId);
    }

    @GetMapping("/{id}")
    public MobileAppointment getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MobileAppointment not found with id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MobileAppointment create(@RequestBody MobileAppointment resource) { return repository.save(resource); }

    @PutMapping("/{id}")
    public MobileAppointment update(@PathVariable Long id, @RequestBody MobileAppointment payload) {
        var entity = getById(id);
        entity.updateFrom(payload);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("MobileAppointment not found with id " + id);
        repository.deleteById(id);
    }
}
