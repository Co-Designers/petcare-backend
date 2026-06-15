package com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "veterinarians")
public class Veterinarian extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false)
    private Long clinicId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String specialty;
    @Column(nullable = false)
    private String licenseNumber;
    private String email;
    private String phone;
    private String photoUrl;
    private Boolean isActive = true;
    private String schedule;

    public Long getClinicId() { return clinicId; }
    public void setClinicId(Long clinicId) { this.clinicId = clinicId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public void updateFrom(Veterinarian source) {
        if (source.clinicId != null) this.clinicId = source.clinicId;
        if (source.name != null) this.name = source.name;
        if (source.specialty != null) this.specialty = source.specialty;
        if (source.licenseNumber != null) this.licenseNumber = source.licenseNumber;
        if (source.email != null) this.email = source.email;
        if (source.phone != null) this.phone = source.phone;
        if (source.photoUrl != null) this.photoUrl = source.photoUrl;
        if (source.isActive != null) this.isActive = source.isActive;
        if (source.schedule != null) this.schedule = source.schedule;
    }
}
