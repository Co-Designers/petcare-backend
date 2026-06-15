package com.petcare.center.platform.contexts.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String email, String userType, String token) {}
