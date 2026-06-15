package com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.ClinicService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicServiceRepository extends JpaRepository<ClinicService, Long> {
    List<ClinicService> findByClinicId(Long clinicId);
}
