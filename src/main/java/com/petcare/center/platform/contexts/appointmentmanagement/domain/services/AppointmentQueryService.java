package com.petcare.center.platform.contexts.appointmentmanagement.domain.services;

import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.aggregates.Appointment;
import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.queries.GetAppointmentByIdQuery;

public interface AppointmentQueryService {
    Appointment handle(GetAppointmentByIdQuery query);
}
