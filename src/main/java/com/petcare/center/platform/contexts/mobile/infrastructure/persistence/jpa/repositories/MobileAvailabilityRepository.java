package com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MobileAvailabilityRepository extends JpaRepository<MobileAvailability, Long> {
    List<MobileAvailability> findByMobileId(Long mobileId);
    List<MobileAvailability> findByMobileIdAndDate(Long mobileId, java.time.LocalDate date);
}
