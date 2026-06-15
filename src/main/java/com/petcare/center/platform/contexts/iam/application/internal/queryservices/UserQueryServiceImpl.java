package com.petcare.center.platform.contexts.iam.application.internal.queryservices;

import com.petcare.center.platform.contexts.iam.domain.model.aggregates.UserAccount;
import com.petcare.center.platform.contexts.iam.domain.model.queries.GetUserByIdQuery;
import com.petcare.center.platform.contexts.iam.domain.services.UserQueryService;
import com.petcare.center.platform.contexts.iam.infrastructure.persistence.jpa.repositories.UserAccountRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserAccountRepository userAccountRepository;

    public UserQueryServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount handle(GetUserByIdQuery query) {
        return userAccountRepository.findById(query.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + query.userId()));
    }

    @Override
    public List<UserAccount> handleGetAll() {
        return userAccountRepository.findAll();
    }
}
