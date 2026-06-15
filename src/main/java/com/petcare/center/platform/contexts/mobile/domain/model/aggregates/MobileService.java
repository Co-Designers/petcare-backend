package com.petcare.center.platform.contexts.mobile.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mobile_services")
public class MobileService extends AuditableAbstractPersistenceEntity {
    private Long mobileId;
    private String name;
    @Column(length = 1000)
    private String description;
    private Integer durationMinutes;
    private BigDecimal price;
    private Boolean isActive = true;
    public Long getMobileId() { return mobileId; }
    public void setMobileId(Long mobileId) { this.mobileId = mobileId; }
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
    public void updateFrom(MobileService source) {
        if (source.mobileId != null) this.mobileId = source.mobileId;
        if (source.name != null) this.name = source.name;
        if (source.description != null) this.description = source.description;
        if (source.durationMinutes != null) this.durationMinutes = source.durationMinutes;
        if (source.price != null) this.price = source.price;
        if (source.isActive != null) this.isActive = source.isActive;
    }
}
