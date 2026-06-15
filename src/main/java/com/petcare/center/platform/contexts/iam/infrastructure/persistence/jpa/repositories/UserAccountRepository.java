package com.petcare.center.platform.contexts.iam.infrastructure.persistence.jpa.repositories;

import com.petcare.center.platform.contexts.iam.domain.model.aggregates.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
