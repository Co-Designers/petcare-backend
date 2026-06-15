package com.petcare.center.platform.contexts.mobile.interfaces.rest;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileRequest;
import com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories.MobileRequestRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mobile-requests")
public class MobileRequestsController {
    private final MobileRequestRepository repository;
    public MobileRequestsController(MobileRequestRepository repository) { this.repository = repository; }

    @GetMapping
    public List<MobileRequest> getAll(@RequestParam(required = false) Long mobileId) {
        return mobileId == null ? repository.findAll() : repository.findByMobileId(mobileId);
    }

    @GetMapping("/{id}")
    public MobileRequest getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MobileRequest not found with id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MobileRequest create(@RequestBody MobileRequest resource) { return repository.save(resource); }

    @PutMapping("/{id}")
    public MobileRequest update(@PathVariable Long id, @RequestBody MobileRequest payload) {
        var entity = getById(id);
        entity.updateFrom(payload);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("MobileRequest not found with id " + id);
        repository.deleteById(id);
    }
}
