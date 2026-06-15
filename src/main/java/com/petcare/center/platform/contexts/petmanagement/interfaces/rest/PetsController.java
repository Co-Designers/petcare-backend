package com.petcare.center.platform.contexts.petmanagement.interfaces.rest;

import com.petcare.center.platform.contexts.petmanagement.domain.model.aggregates.Pet;
import com.petcare.center.platform.contexts.petmanagement.infrastructure.persistence.jpa.repositories.PetRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
public class PetsController {
    private final PetRepository petRepository;

    public PetsController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping
    public List<Pet> getPets(@RequestParam(required = false) Long ownerId) {
        return ownerId == null ? petRepository.findAll() : petRepository.findByOwnerId(ownerId);
    }

    @GetMapping("/{petId}")
    public Pet getPetById(@PathVariable Long petId) {
        return petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet not found with id " + petId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    @PutMapping("/{petId}")
    public Pet updatePet(@PathVariable Long petId, @RequestBody Pet payload) {
        var pet = getPetById(petId);
        pet.updateFrom(payload);
        return petRepository.save(pet);
    }

    @DeleteMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable Long petId) {
        if (!petRepository.existsById(petId)) throw new ResourceNotFoundException("Pet not found with id " + petId);
        petRepository.deleteById(petId);
    }
}
