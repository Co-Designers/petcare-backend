package com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<Clinic> findByDistrictIgnoreCase(String district);
}
