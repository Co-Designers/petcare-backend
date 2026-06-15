package com.petcare.center.platform.contexts.appointmentmanagement.domain.services;

import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.aggregates.Appointment;
import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.commands.CreateAppointmentCommand;

public interface AppointmentCommandService {
    Appointment handle(CreateAppointmentCommand command);
}
