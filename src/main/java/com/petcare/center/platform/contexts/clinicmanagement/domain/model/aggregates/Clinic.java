package com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clinics")
public class Clinic extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    private String address;
    private String district;
    private String ruc;
    private String clinicType;
    @Column(length = 1000)
    private String description;
    private String logoUrl;
    private String openingHours;
    private Double rating;

    @ElementCollection
    @CollectionTable(name = "clinic_services_offered", joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "service_name")
    private List<String> servicesOffered = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "clinic_specialties", joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "specialty")
    private List<String> specialties = new ArrayList<>();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getClinicType() { return clinicType; }
    public void setClinicType(String clinicType) { this.clinicType = clinicType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public List<String> getServicesOffered() { return servicesOffered; }
    public void setServicesOffered(List<String> servicesOffered) { this.servicesOffered = servicesOffered == null ? new ArrayList<>() : servicesOffered; }
    public List<String> getSpecialties() { return specialties; }
    public void setSpecialties(List<String> specialties) { this.specialties = specialties == null ? new ArrayList<>() : specialties; }

    public void updateFrom(Clinic source) {
        if (source.name != null) this.name = source.name;
        if (source.email != null) this.email = source.email;
        if (source.phone != null) this.phone = source.phone;
        if (source.address != null) this.address = source.address;
        if (source.district != null) this.district = source.district;
        if (source.ruc != null) this.ruc = source.ruc;
        if (source.clinicType != null) this.clinicType = source.clinicType;
        if (source.description != null) this.description = source.description;
        if (source.logoUrl != null) this.logoUrl = source.logoUrl;
        if (source.openingHours != null) this.openingHours = source.openingHours;
        if (source.servicesOffered != null && !source.servicesOffered.isEmpty()) this.servicesOffered = source.servicesOffered;
        if (source.specialties != null && !source.specialties.isEmpty()) this.specialties = source.specialties;
        if (source.rating != null) this.rating = source.rating;
    }
}
