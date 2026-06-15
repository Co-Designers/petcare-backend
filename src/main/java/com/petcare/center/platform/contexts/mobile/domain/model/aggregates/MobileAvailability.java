package com.petcare.center.platform.contexts.mobile.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "mobile_availability")
public class MobileAvailability extends AuditableAbstractPersistenceEntity {
    private Long mobileId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isAvailable = true;
    public Long getMobileId() { return mobileId; }
    public void setMobileId(Long mobileId) { this.mobileId = mobileId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean available) { isAvailable = available; }
    public void updateFrom(MobileAvailability source) {
        if (source.mobileId != null) this.mobileId = source.mobileId;
        if (source.date != null) this.date = source.date;
        if (source.startTime != null) this.startTime = source.startTime;
        if (source.endTime != null) this.endTime = source.endTime;
        if (source.isAvailable != null) this.isAvailable = source.isAvailable;
    }
}
