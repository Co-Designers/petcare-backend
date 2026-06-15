package com.petcare.center.platform.contexts.mobile.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mobile_professionals")
public class MobileProfessional extends AuditableAbstractPersistenceEntity {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String mobileSubtype;
    private Boolean hasVehicle = false;
    private String vehiclePlate;
    private String specialty;
    private Double rating;
    private Boolean isActive = true;

    @ElementCollection
    @CollectionTable(name = "mobile_professional_coverage_districts", joinColumns = @JoinColumn(name = "mobile_professional_id"))
    @Column(name = "district")
    private List<String> coverageDistricts = new ArrayList<>();

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getMobileSubtype() { return mobileSubtype; }
    public void setMobileSubtype(String mobileSubtype) { this.mobileSubtype = mobileSubtype; }
    public List<String> getCoverageDistricts() { return coverageDistricts; }
    public void setCoverageDistricts(List<String> coverageDistricts) { this.coverageDistricts = coverageDistricts == null ? new ArrayList<>() : coverageDistricts; }
    public Boolean getHasVehicle() { return hasVehicle; }
    public void setHasVehicle(Boolean hasVehicle) { this.hasVehicle = hasVehicle; }
    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }

    public void updateFrom(MobileProfessional source) {
        if (source.userId != null) this.userId = source.userId;
        if (source.name != null) this.name = source.name;
        if (source.email != null) this.email = source.email;
        if (source.phone != null) this.phone = source.phone;
        if (source.mobileSubtype != null) this.mobileSubtype = source.mobileSubtype;
        if (source.coverageDistricts != null && !source.coverageDistricts.isEmpty()) this.coverageDistricts = source.coverageDistricts;
        if (source.hasVehicle != null) this.hasVehicle = source.hasVehicle;
        if (source.vehiclePlate != null) this.vehiclePlate = source.vehiclePlate;
        if (source.specialty != null) this.specialty = source.specialty;
        if (source.rating != null) this.rating = source.rating;
        if (source.isActive != null) this.isActive = source.isActive;
    }
}
