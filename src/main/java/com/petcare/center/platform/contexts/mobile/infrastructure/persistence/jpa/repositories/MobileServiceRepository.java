package com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MobileServiceRepository extends JpaRepository<MobileService, Long> {
    List<MobileService> findByMobileId(Long mobileId);
}
