package com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByClinicId(Long clinicId);
    List<Patient> findByPetId(Long petId);
}
