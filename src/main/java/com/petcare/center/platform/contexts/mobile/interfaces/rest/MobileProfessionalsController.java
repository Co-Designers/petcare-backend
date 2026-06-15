package com.petcare.center.platform.contexts.mobile.interfaces.rest;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileProfessional;
import com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories.MobileProfessionalRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mobile-professionals")
public class MobileProfessionalsController {
    private final MobileProfessionalRepository repository;
    public MobileProfessionalsController(MobileProfessionalRepository repository) { this.repository = repository; }

    @GetMapping
    public List<MobileProfessional> getAll(@RequestParam(required = false) Long userId) {
        return userId == null ? repository.findAll() : repository.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public MobileProfessional getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MobileProfessional not found with id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MobileProfessional create(@RequestBody MobileProfessional resource) { return repository.save(resource); }

    @PutMapping("/{id}")
    public MobileProfessional update(@PathVariable Long id, @RequestBody MobileProfessional payload) {
        var entity = getById(id);
        entity.updateFrom(payload);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("MobileProfessional not found with id " + id);
        repository.deleteById(id);
    }
}
