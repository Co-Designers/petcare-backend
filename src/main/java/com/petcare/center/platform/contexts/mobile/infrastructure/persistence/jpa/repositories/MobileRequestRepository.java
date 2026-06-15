package com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MobileRequestRepository extends JpaRepository<MobileRequest, Long> {
    List<MobileRequest> findByMobileId(Long mobileId);
}
