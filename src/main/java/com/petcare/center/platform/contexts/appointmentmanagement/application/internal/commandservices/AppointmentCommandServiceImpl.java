package com.petcare.center.platform.contexts.appointmentmanagement.application.internal.commandservices;

import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.aggregates.Appointment;
import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.commands.CreateAppointmentCommand;
import com.petcare.center.platform.contexts.appointmentmanagement.domain.services.AppointmentCommandService;
import com.petcare.center.platform.contexts.appointmentmanagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment handle(CreateAppointmentCommand command) {
        var appointment = new Appointment();
        appointment.setPetId(command.petId());
        appointment.setProviderId(command.providerId());
        appointment.setProviderType(command.providerType());
        appointment.setServiceType(command.serviceType());
        appointment.setDateTime(command.dateTime());
        appointment.setStatus("pending");
        appointment.setPaymentStatus("pending");
        return appointmentRepository.save(appointment);
    }
}
