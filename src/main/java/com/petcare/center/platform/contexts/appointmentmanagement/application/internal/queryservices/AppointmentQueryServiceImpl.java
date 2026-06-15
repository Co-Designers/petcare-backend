package com.petcare.center.platform.contexts.appointmentmanagement.application.internal.queryservices;

import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.aggregates.Appointment;
import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.queries.GetAppointmentByIdQuery;
import com.petcare.center.platform.contexts.appointmentmanagement.domain.services.AppointmentQueryService;
import com.petcare.center.platform.contexts.appointmentmanagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;
    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) { this.appointmentRepository = appointmentRepository; }
    @Override
    public Appointment handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + query.appointmentId()));
    }
}
