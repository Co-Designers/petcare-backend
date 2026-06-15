package com.petcare.center.platform.contexts.mobile.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "mobile_requests")
public class MobileRequest extends AuditableAbstractPersistenceEntity {
    private Long mobileId;
    private Long ownerId;
    private Long petId;
    private Long serviceId;
    private String status = "pending";
    private Instant scheduledDateTime;
    private String address;
    @Column(length = 1000)
    private String notes;
    private Instant createdAtClient;

    public Long getMobileId() { return mobileId; }
    public void setMobileId(Long mobileId) { this.mobileId = mobileId; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }
    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Instant getScheduledDateTime() { return scheduledDateTime; }
    public void setScheduledDateTime(Instant scheduledDateTime) { this.scheduledDateTime = scheduledDateTime; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Instant getCreatedAt() { return createdAtClient; }
    public void setCreatedAt(Instant createdAt) { this.createdAtClient = createdAt; }
    public void updateFrom(MobileRequest source) {
        if (source.mobileId != null) this.mobileId = source.mobileId;
        if (source.ownerId != null) this.ownerId = source.ownerId;
        if (source.petId != null) this.petId = source.petId;
        if (source.serviceId != null) this.serviceId = source.serviceId;
        if (source.status != null) this.status = source.status;
        if (source.scheduledDateTime != null) this.scheduledDateTime = source.scheduledDateTime;
        if (source.address != null) this.address = source.address;
        if (source.notes != null) this.notes = source.notes;
        if (source.createdAtClient != null) this.createdAtClient = source.createdAtClient;
    }
}
