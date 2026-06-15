package com.petcare.center.platform.contexts.medicalrecords.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.medicalrecords.domain.model.aggregates.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPetId(Long petId);
}
