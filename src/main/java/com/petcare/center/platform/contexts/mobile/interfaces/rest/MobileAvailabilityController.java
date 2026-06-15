package com.petcare.center.platform.contexts.mobile.interfaces.rest;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileAvailability;
import com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories.MobileAvailabilityRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mobile-availability")
public class MobileAvailabilityController {
    private final MobileAvailabilityRepository repository;
    public MobileAvailabilityController(MobileAvailabilityRepository repository) { this.repository = repository; }
    @GetMapping
    public List<MobileAvailability> getAll(@RequestParam(required = false) Long mobileId, @RequestParam(required = false) LocalDate date) {
        if (mobileId != null && date != null) return repository.findByMobileIdAndDate(mobileId, date);
        if (mobileId != null) return repository.findByMobileId(mobileId);
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public MobileAvailability getById(@PathVariable Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MobileAvailability not found with id " + id)); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MobileAvailability create(@RequestBody MobileAvailability resource) { return repository.save(resource); }
    @PutMapping("/{id}")
    public MobileAvailability update(@PathVariable Long id, @RequestBody MobileAvailability payload) { var entity = getById(id); entity.updateFrom(payload); return repository.save(entity); }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { if (!repository.existsById(id)) throw new ResourceNotFoundException("MobileAvailability not found with id " + id); repository.deleteById(id); }
}
