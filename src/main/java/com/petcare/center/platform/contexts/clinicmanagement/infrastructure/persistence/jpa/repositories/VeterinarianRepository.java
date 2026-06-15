package com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
    List<Veterinarian> findByClinicId(Long clinicId);
}
