package com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MobileAppointmentRepository extends JpaRepository<MobileAppointment, Long> {
    List<MobileAppointment> findByMobileId(Long mobileId);
}
