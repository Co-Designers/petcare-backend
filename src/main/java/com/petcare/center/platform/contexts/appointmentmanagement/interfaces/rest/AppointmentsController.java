package com.petcare.center.platform.contexts.appointmentmanagement.interfaces.rest;

import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.aggregates.Appointment;
import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.queries.GetAppointmentByIdQuery;
import com.petcare.center.platform.contexts.appointmentmanagement.domain.services.AppointmentQueryService;
import com.petcare.center.platform.contexts.appointmentmanagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import com.petcare.center.platform.contexts.petmanagement.infrastructure.persistence.jpa.repositories.PetRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentQueryService appointmentQueryService;
    private final PetRepository petRepository;

    public AppointmentsController(AppointmentRepository appointmentRepository, AppointmentQueryService appointmentQueryService, PetRepository petRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentQueryService = appointmentQueryService;
        this.petRepository = petRepository;
    }

    @GetMapping
    public List<Appointment> getAppointments(@RequestParam(required = false) Long ownerId,
                                             @RequestParam(required = false) Long clinicId,
                                             @RequestParam(required = false) Long petId) {
        if (ownerId != null) return appointmentRepository.findByOwnerId(ownerId);
        if (clinicId != null) return appointmentRepository.findByClinicId(clinicId);
        if (petId != null) return appointmentRepository.findByPetId(petId);
        return appointmentRepository.findAll();
    }

    @GetMapping("/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable Long appointmentId) {
        return appointmentQueryService.handle(new GetAppointmentByIdQuery(appointmentId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        normalize(appointment);
        return appointmentRepository.save(appointment);
    }

    @PutMapping("/{appointmentId}")
    public Appointment updateAppointment(@PathVariable Long appointmentId, @RequestBody Appointment payload) {
        var appointment = getAppointmentById(appointmentId);
        appointment.updateFrom(payload);
        normalize(appointment);
        return appointmentRepository.save(appointment);
    }

    @DeleteMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) throw new ResourceNotFoundException("Appointment not found with id " + appointmentId);
        appointmentRepository.deleteById(appointmentId);
    }

    private void normalize(Appointment appointment) {
        if (appointment.getOwnerId() == null && appointment.getPetId() != null) {
            petRepository.findById(appointment.getPetId()).ifPresent(pet -> appointment.setOwnerId(pet.getOwnerId()));
        }
        if (appointment.getProviderType() == null && appointment.getClinicId() != null) appointment.setProviderType("clinic");
        if (appointment.getProviderId() == null && appointment.getClinicId() != null) appointment.setProviderId(appointment.getClinicId());
        if (appointment.getClinicId() == null && "clinic".equalsIgnoreCase(appointment.getProviderType())) appointment.setClinicId(appointment.getProviderId());
        if (appointment.getStatus() == null) appointment.setStatus("pending");
        if (appointment.getPaymentStatus() == null) appointment.setPaymentStatus("pending");
    }
}
