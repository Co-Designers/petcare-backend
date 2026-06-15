package com.petcare.center.platform.contexts.appointmentmanagement.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "appointments")
public class Appointment extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false)
    private Long petId;
    private Long ownerId;
    private Long providerId;
    private String providerType;
    private Long clinicId;
    private Long veterinarianId;
    @Column(nullable = false)
    private String serviceType;
    @Column(nullable = false)
    private Instant dateTime;
    @Column(nullable = false)
    private String status = "pending";
    private String paymentStatus = "pending";
    @Column(length = 1000)
    private String notes;

    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public Long getProviderId() { return providerId; }
    public void setProviderId(Long providerId) { this.providerId = providerId; }
    public String getProviderType() { return providerType; }
    public void setProviderType(String providerType) { this.providerType = providerType; }
    public Long getClinicId() { return clinicId; }
    public void setClinicId(Long clinicId) { this.clinicId = clinicId; }
    public Long getVeterinarianId() { return veterinarianId; }
    public void setVeterinarianId(Long veterinarianId) { this.veterinarianId = veterinarianId; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public Instant getDateTime() { return dateTime; }
    public void setDateTime(Instant dateTime) { this.dateTime = dateTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public void updateFrom(Appointment source) {
        if (source.petId != null) this.petId = source.petId;
        if (source.ownerId != null) this.ownerId = source.ownerId;
        if (source.providerId != null) this.providerId = source.providerId;
        if (source.providerType != null) this.providerType = source.providerType;
        if (source.clinicId != null) this.clinicId = source.clinicId;
        if (source.veterinarianId != null) this.veterinarianId = source.veterinarianId;
        if (source.serviceType != null) this.serviceType = source.serviceType;
        if (source.dateTime != null) this.dateTime = source.dateTime;
        if (source.status != null) this.status = source.status;
        if (source.paymentStatus != null) this.paymentStatus = source.paymentStatus;
        if (source.notes != null) this.notes = source.notes;
    }
}
