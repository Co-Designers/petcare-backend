package com.petcare.center.platform.contexts.iam.interfaces.rest;

import com.petcare.center.platform.contexts.iam.domain.model.queries.GetUserByIdQuery;
import com.petcare.center.platform.contexts.iam.domain.services.UserQueryService;
import com.petcare.center.platform.contexts.iam.infrastructure.persistence.jpa.repositories.UserAccountRepository;
import com.petcare.center.platform.contexts.iam.interfaces.rest.resources.ChangePasswordResource;
import com.petcare.center.platform.contexts.iam.interfaces.rest.resources.UserResource;
import com.petcare.center.platform.contexts.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.petcare.center.platform.contexts.shared.domain.exceptions.BusinessRuleViolationException;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import com.petcare.center.platform.contexts.shared.interfaces.rest.resources.MessageResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UserQueryService userQueryService;
    private final UserAccountRepository userAccountRepository;

    public UsersController(UserQueryService userQueryService, UserAccountRepository userAccountRepository) {
        this.userQueryService = userQueryService;
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping
    public List<UserResource> getAllUsers() {
        return userQueryService.handleGetAll().stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
    }

    @GetMapping("/me")
    public UserResource getMe(@RequestHeader(value = "Authorization", required = false) String authorization) {
        var userId = extractUserIdFromToken(authorization);
        return UserResourceFromEntityAssembler.toResourceFromEntity(userQueryService.handle(new GetUserByIdQuery(userId)));
    }

    @GetMapping("/{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return UserResourceFromEntityAssembler.toResourceFromEntity(userQueryService.handle(new GetUserByIdQuery(userId)));
    }

    @PatchMapping("/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UserResource resource) {
        var user = userAccountRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        user.updateProfile(resource.fullName(), resource.email(), resource.phone(), resource.district());
        return UserResourceFromEntityAssembler.toResourceFromEntity(userAccountRepository.save(user));
    }

    @PostMapping("/change-password")
    public MessageResource changePassword(@RequestBody ChangePasswordResource resource) {
        var user = userAccountRepository.findById(resource.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + resource.userId()));
        if (!user.getPassword().equals(resource.currentPassword())) {
            throw new BusinessRuleViolationException("Current password is invalid");
        }
        user.setPassword(resource.newPassword());
        userAccountRepository.save(user);
        return new MessageResource("Password changed successfully");
    }

    private Long extractUserIdFromToken(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer petcare-token-")) {
            return Long.parseLong(authorization.substring("Bearer petcare-token-".length()));
        }
        return userAccountRepository.findAll().stream().findFirst().map(u -> u.getId()).orElseThrow(() -> new ResourceNotFoundException("No current user available"));
    }
}
