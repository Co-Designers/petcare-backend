package com.petcare.center.platform.contexts.iam.domain.services;

import com.petcare.center.platform.contexts.iam.domain.model.aggregates.UserAccount;
import com.petcare.center.platform.contexts.iam.domain.model.commands.SignInCommand;
import com.petcare.center.platform.contexts.iam.domain.model.commands.SignUpCommand;

public interface UserCommandService {
    UserAccount handle(SignUpCommand command);
    UserAccount handle(SignInCommand command);
}
