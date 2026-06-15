package com.petcare.center.platform.contexts.iam.interfaces.rest;

import com.petcare.center.platform.contexts.iam.domain.model.commands.SignInCommand;
import com.petcare.center.platform.contexts.iam.domain.model.commands.SignUpCommand;
import com.petcare.center.platform.contexts.iam.domain.services.UserCommandService;
import com.petcare.center.platform.contexts.iam.interfaces.rest.resources.*;
import com.petcare.center.platform.contexts.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody @Valid SignInResource resource) {
        var user = userCommandService.handle(new SignInCommand(resource.username(), resource.password()));
        return ResponseEntity.ok(new AuthenticatedUserResource(user.getId(), user.getUsername(), user.getEmail(), user.getUserType(), "petcare-token-" + user.getId()));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody @Valid SignUpResource resource) {
        var user = userCommandService.handle(new SignUpCommand(
                resource.username(), resource.password(), resource.email(), resource.userType(), resource.fullName(), resource.phone(),
                resource.district(), resource.clinicName(), resource.ruc(), resource.address(), resource.clinicType(), resource.mobileSubtype(),
                resource.coverageDistricts(), resource.hasVehicle(), resource.vehiclePlate(), resource.specialty()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResourceFromEntityAssembler.toResourceFromEntity(user));
    }
}
