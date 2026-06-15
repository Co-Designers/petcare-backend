package com.petcare.center.platform.contexts.iam.domain.services;

import com.petcare.center.platform.contexts.iam.domain.model.aggregates.UserAccount;
import com.petcare.center.platform.contexts.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;

public interface UserQueryService {
    UserAccount handle(GetUserByIdQuery query);
    List<UserAccount> handleGetAll();
}
