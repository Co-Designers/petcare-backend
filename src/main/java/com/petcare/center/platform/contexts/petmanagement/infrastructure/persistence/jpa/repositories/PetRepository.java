package com.petcare.center.platform.contexts.petmanagement.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.petmanagement.domain.model.aggregates.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByOwnerId(Long ownerId);
}
