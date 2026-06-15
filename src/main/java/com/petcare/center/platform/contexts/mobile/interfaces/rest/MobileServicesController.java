package com.petcare.center.platform.contexts.mobile.interfaces.rest;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileService;
import com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories.MobileServiceRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mobile-services")
public class MobileServicesController {
    private final MobileServiceRepository repository;
    public MobileServicesController(MobileServiceRepository repository) { this.repository = repository; }

    @GetMapping
    public List<MobileService> getAll(@RequestParam(required = false) Long mobileId) {
        return mobileId == null ? repository.findAll() : repository.findByMobileId(mobileId);
    }

    @GetMapping("/{id}")
    public MobileService getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MobileService not found with id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MobileService create(@RequestBody MobileService resource) { return repository.save(resource); }

    @PutMapping("/{id}")
    public MobileService update(@PathVariable Long id, @RequestBody MobileService payload) {
        var entity = getById(id);
        entity.updateFrom(payload);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("MobileService not found with id " + id);
        repository.deleteById(id);
    }
}
