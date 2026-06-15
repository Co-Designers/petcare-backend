package com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "clinic_services")
public class ClinicService extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false)
    private Long clinicId;
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    private Integer durationMinutes;
    private BigDecimal price;
    private Boolean isActive = true;

    public Long getClinicId() { return clinicId; }
    public void setClinicId(Long clinicId) { this.clinicId = clinicId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }
    public void updateFrom(ClinicService source) {
        if (source.clinicId != null) this.clinicId = source.clinicId;
        if (source.name != null) this.name = source.name;
        if (source.description != null) this.description = source.description;
        if (source.durationMinutes != null) this.durationMinutes = source.durationMinutes;
        if (source.price != null) this.price = source.price;
        if (source.isActive != null) this.isActive = source.isActive;
    }
}
