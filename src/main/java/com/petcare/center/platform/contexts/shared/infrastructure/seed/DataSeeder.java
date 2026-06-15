package com.petcare.center.platform.contexts.shared.infrastructure.seed;

import com.petcare.center.platform.contexts.appointmentmanagement.domain.model.aggregates.Appointment;
import com.petcare.center.platform.contexts.appointmentmanagement.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.*;
import com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories.*;
import com.petcare.center.platform.contexts.iam.domain.model.aggregates.UserAccount;
import com.petcare.center.platform.contexts.iam.infrastructure.persistence.jpa.repositories.UserAccountRepository;
import com.petcare.center.platform.contexts.medicalrecords.domain.model.aggregates.MedicalRecord;
import com.petcare.center.platform.contexts.medicalrecords.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.*;
import com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories.*;
import com.petcare.center.platform.contexts.petmanagement.domain.model.aggregates.Pet;
import com.petcare.center.platform.contexts.petmanagement.infrastructure.persistence.jpa.repositories.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final UserAccountRepository users;
    private final PetRepository pets;
    private final ClinicRepository clinics;
    private final VeterinarianRepository veterinarians;
    private final ClinicServiceRepository services;
    private final PatientRepository patients;
    private final AppointmentRepository appointments;
    private final MedicalRecordRepository medicalRecords;
    private final MobileProfessionalRepository mobileProfessionals;
    private final MobileServiceRepository mobileServices;
    private final MobileAvailabilityRepository mobileAvailability;
    private final MobileRequestRepository mobileRequests;
    private final MobileAppointmentRepository mobileAppointments;

    public DataSeeder(UserAccountRepository users, PetRepository pets, ClinicRepository clinics, VeterinarianRepository veterinarians, ClinicServiceRepository services, PatientRepository patients, AppointmentRepository appointments, MedicalRecordRepository medicalRecords, MobileProfessionalRepository mobileProfessionals, MobileServiceRepository mobileServices, MobileAvailabilityRepository mobileAvailability, MobileRequestRepository mobileRequests, MobileAppointmentRepository mobileAppointments) {
        this.users = users;
        this.pets = pets;
        this.clinics = clinics;
        this.veterinarians = veterinarians;
        this.services = services;
        this.patients = patients;
        this.appointments = appointments;
        this.medicalRecords = medicalRecords;
        this.mobileProfessionals = mobileProfessionals;
        this.mobileServices = mobileServices;
        this.mobileAvailability = mobileAvailability;
        this.mobileRequests = mobileRequests;
        this.mobileAppointments = mobileAppointments;
    }

    @Override
    public void run(String... args) {
        if (users.count() > 0) return;

        var clinicUser = new UserAccount("clinic1", "clinic123", "clinic@petcare.com", "CLINIC", "Clínica San Martín", "999888777");
        clinicUser.setDistrict("San Isidro");
        clinicUser.setClinicName("Veterinaria San Martín");
        clinicUser.setRuc("20601234567");
        clinicUser.setAddress("Av. Javier Prado 1234");
        clinicUser.setClinicType("General");
        users.save(clinicUser);

        var owner = new UserAccount("owner1", "owner123", "owner@petcare.com", "OWNER", "Ana Torres", "987654321");
        owner.setDistrict("Miraflores");
        users.save(owner);

        var mobileUser = new UserAccount("mobile1", "mobile123", "mobile@petcare.com", "MOBILE", "Dra. Valeria Ramos", "988777666");
        mobileUser.setMobileSubtype("vet");
        mobileUser.setCoverageDistricts(List.of("Miraflores", "San Isidro", "Surco"));
        mobileUser.setHasVehicle(true);
        mobileUser.setVehiclePlate("PET-321");
        mobileUser.setSpecialty("Urgencias y atención a domicilio");
        users.save(mobileUser);

        var clinic = new Clinic();
        clinic.setName("Veterinaria San Martín");
        clinic.setEmail("contacto@sanmartinvet.com");
        clinic.setPhone("999888777");
        clinic.setAddress("Av. Javier Prado 1234");
        clinic.setDistrict("San Isidro");
        clinic.setRuc("20601234567");
        clinic.setClinicType("General");
        clinic.setDescription("Clínica veterinaria con atención preventiva, vacunación, cirugía menor y emergencias diurnas.");
        clinic.setOpeningHours("Lun-Vie 08:00-20:00, Sáb 09:00-14:00");
        clinic.setServicesOffered(List.of("Consulta general", "Vacunación", "Ecografía", "Cirugía menor"));
        clinic.setSpecialties(List.of("Medicina general", "Odontología", "Cardiología"));
        clinic.setRating(4.7);
        clinics.save(clinic);

        var clinic2 = new Clinic();
        clinic2.setName("Clínica Veterinaria Central");
        clinic2.setEmail("info@clinicacentral.com");
        clinic2.setPhone("987654321");
        clinic2.setAddress("Calle Principal 567");
        clinic2.setDistrict("Miraflores");
        clinic2.setRuc("20765432123");
        clinic2.setClinicType("Emergency 24h");
        clinic2.setDescription("Centro veterinario 24 horas especializado en emergencias y diagnóstico por imágenes.");
        clinic2.setOpeningHours("Lun-Dom 24h");
        clinic2.setServicesOffered(List.of("Emergencia", "Laboratorio", "Hospitalización"));
        clinic2.setSpecialties(List.of("Urgencias", "Diagnóstico", "Cirugía"));
        clinic2.setRating(4.8);
        clinics.save(clinic2);

        var vet = new Veterinarian();
        vet.setClinicId(clinic.getId());
        vet.setName("Dr. Rafael González");
        vet.setSpecialty("Medicina general");
        vet.setLicenseNumber("VET-001-2026");
        vet.setEmail("rafael@sanmartinvet.com");
        vet.setPhone("999111222");
        vet.setIsActive(true);
        vet.setSchedule("Lun-Vie 09:00-18:00");
        veterinarians.save(vet);

        var vet2 = new Veterinarian();
        vet2.setClinicId(clinic.getId());
        vet2.setName("Dra. María Rodríguez");
        vet2.setSpecialty("Cirugía menor");
        vet2.setLicenseNumber("VET-002-2026");
        vet2.setEmail("maria@sanmartinvet.com");
        vet2.setPhone("999111223");
        vet2.setIsActive(true);
        vet2.setSchedule("Mar-Sáb 10:00-19:00");
        veterinarians.save(vet2);

        var service = new ClinicService();
        service.setClinicId(clinic.getId());
        service.setName("Consulta general");
        service.setDescription("Evaluación clínica completa de la mascota.");
        service.setDurationMinutes(30);
        service.setPrice(new BigDecimal("80.00"));
        service.setIsActive(true);
        services.save(service);

        var service2 = new ClinicService();
        service2.setClinicId(clinic.getId());
        service2.setName("Vacunación");
        service2.setDescription("Aplicación de vacunas y registro de calendario sanitario.");
        service2.setDurationMinutes(20);
        service2.setPrice(new BigDecimal("60.00"));
        service2.setIsActive(true);
        services.save(service2);

        var pet = new Pet();
        pet.setOwnerId(owner.getId());
        pet.setName("Max");
        pet.setSpecies("dog");
        pet.setBreed("Golden Retriever");
        pet.setBirthDate(LocalDate.of(2020, 5, 10));
        pet.setWeight(28.0);
        pet.setAllergies("Sin alergias reportadas");
        pets.save(pet);

        var pet2 = new Pet();
        pet2.setOwnerId(owner.getId());
        pet2.setName("Luna");
        pet2.setSpecies("cat");
        pet2.setBreed("Persa");
        pet2.setBirthDate(LocalDate.of(2021, 8, 15));
        pet2.setWeight(4.5);
        pet2.setAllergies("Polen");
        pets.save(pet2);

        var patient = new Patient();
        patient.setPetId(pet.getId());
        patient.setClinicId(clinic.getId());
        patient.setName(pet.getName());
        patient.setSpecies(pet.getSpecies());
        patient.setBreed(pet.getBreed());
        patient.setBirthDate(pet.getBirthDate());
        patient.setWeight(pet.getWeight());
        patient.setOwnerId(owner.getId());
        patient.setOwnerName(owner.getFullName());
        patient.setOwnerPhone(owner.getPhone());
        patient.setAllergies(pet.getAllergies());
        patient.setLastVisit(Instant.parse("2026-06-10T15:00:00Z"));
        patients.save(patient);

        var appointment = new Appointment();
        appointment.setPetId(pet.getId());
        appointment.setOwnerId(owner.getId());
        appointment.setProviderId(clinic.getId());
        appointment.setProviderType("clinic");
        appointment.setClinicId(clinic.getId());
        appointment.setVeterinarianId(vet.getId());
        appointment.setServiceType("Consulta general");
        appointment.setDateTime(Instant.parse("2026-06-20T15:00:00Z"));
        appointment.setStatus("confirmed");
        appointment.setPaymentStatus("paid");
        appointment.setNotes("Control preventivo anual.");
        appointments.save(appointment);

        var record = new MedicalRecord();
        record.setPetId(pet.getId());
        record.setAppointmentId(appointment.getId());
        record.setDate(Instant.parse("2026-06-10T15:00:00Z"));
        record.setVeterinarianName(vet.getName());
        record.setClinicName(clinic.getName());
        record.setDiagnosis("Paciente clínicamente estable.");
        record.setTreatment("Control nutricional y actividad moderada.");
        record.setVaccinationName("Rabia");
        record.setNextDueDate(LocalDate.of(2027, 6, 10));
        medicalRecords.save(record);

        var mobile = new MobileProfessional();
        mobile.setUserId(mobileUser.getId());
        mobile.setName("Dra. Valeria Ramos");
        mobile.setEmail("valeria.mobile@petcare.com");
        mobile.setPhone("988777666");
        mobile.setMobileSubtype("vet");
        mobile.setCoverageDistricts(List.of("Miraflores", "San Isidro", "Surco"));
        mobile.setHasVehicle(true);
        mobile.setVehiclePlate("PET-321");
        mobile.setSpecialty("Urgencias y atención a domicilio");
        mobile.setRating(4.9);
        mobile.setIsActive(true);
        mobileProfessionals.save(mobile);

        var mobileService = new MobileService();
        mobileService.setMobileId(mobile.getId());
        mobileService.setName("Consulta a domicilio");
        mobileService.setDescription("Evaluación general en el domicilio del dueño.");
        mobileService.setDurationMinutes(45);
        mobileService.setPrice(new BigDecimal("120.00"));
        mobileService.setIsActive(true);
        mobileServices.save(mobileService);

        var slot = new MobileAvailability();
        slot.setMobileId(mobile.getId());
        slot.setDate(LocalDate.of(2026, 6, 25));
        slot.setStartTime(LocalTime.of(9, 0));
        slot.setEndTime(LocalTime.of(13, 0));
        slot.setIsAvailable(true);
        mobileAvailability.save(slot);

        var request = new MobileRequest();
        request.setMobileId(mobile.getId());
        request.setOwnerId(owner.getId());
        request.setPetId(pet.getId());
        request.setServiceId(mobileService.getId());
        request.setStatus("pending");
        request.setScheduledDateTime(Instant.parse("2026-06-25T10:00:00Z"));
        request.setAddress("Calle Los Pinos 123, Miraflores");
        request.setNotes("Max se pone nervioso con traslados.");
        request.setCreatedAt(Instant.parse("2026-06-18T08:00:00Z"));
        mobileRequests.save(request);

        var mobileAppointment = new MobileAppointment();
        mobileAppointment.setMobileId(mobile.getId());
        mobileAppointment.setOwnerId(owner.getId());
        mobileAppointment.setPetId(pet.getId());
        mobileAppointment.setServiceName("Consulta a domicilio");
        mobileAppointment.setScheduledDateTime(Instant.parse("2026-06-25T10:00:00Z"));
        mobileAppointment.setAddress("Calle Los Pinos 123, Miraflores");
        mobileAppointment.setStatus("confirmed");
        mobileAppointment.setNotes("Llevar cartilla de vacunación.");
        mobileAppointments.save(mobileAppointment);
    }
}
