package com.petcare.center.platform.contexts.mobile.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "mobile_appointments")
public class MobileAppointment extends AuditableAbstractPersistenceEntity {
    private Long mobileId;
    private Long ownerId;
    private Long petId;
    private String serviceName;
    private Instant scheduledDateTime;
    private String address;
    private String status = "confirmed";
    @Column(length = 1000)
    private String notes;
    public Long getMobileId() { return mobileId; }
    public void setMobileId(Long mobileId) { this.mobileId = mobileId; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public Instant getScheduledDateTime() { return scheduledDateTime; }
    public void setScheduledDateTime(Instant scheduledDateTime) { this.scheduledDateTime = scheduledDateTime; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public void updateFrom(MobileAppointment source) {
        if (source.mobileId != null) this.mobileId = source.mobileId;
        if (source.ownerId != null) this.ownerId = source.ownerId;
        if (source.petId != null) this.petId = source.petId;
        if (source.serviceName != null) this.serviceName = source.serviceName;
        if (source.scheduledDateTime != null) this.scheduledDateTime = source.scheduledDateTime;
        if (source.address != null) this.address = source.address;
        if (source.status != null) this.status = source.status;
        if (source.notes != null) this.notes = source.notes;
    }
}
