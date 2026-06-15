package com.petcare.center.platform.contexts.appointmentmanagement.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByOwnerId(Long ownerId);
    List<Appointment> findByClinicId(Long clinicId);
    List<Appointment> findByPetId(Long petId);
}
