package com.petcare.center.platform.contexts.appointmentmanagement.domain.model.commands;

import java.time.Instant;

public record CreateAppointmentCommand(Long petId, Long providerId, String providerType, String serviceType, Instant dateTime) {}
