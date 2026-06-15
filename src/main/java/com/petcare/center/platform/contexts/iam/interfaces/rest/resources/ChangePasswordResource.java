package com.petcare.center.platform.contexts.iam.interfaces.rest.resources;

public record ChangePasswordResource(Long userId, String currentPassword, String newPassword) {}
