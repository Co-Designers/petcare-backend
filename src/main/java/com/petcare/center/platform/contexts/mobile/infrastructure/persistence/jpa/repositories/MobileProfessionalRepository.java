package com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileProfessional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MobileProfessionalRepository extends JpaRepository<MobileProfessional, Long> {
    List<MobileProfessional> findByUserId(Long userId);
}
