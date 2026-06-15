package com.petcare.center.platform.contexts.iam.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_accounts")
public class UserAccount extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String userType;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String phone;
    private String district;
    private String clinicName;
    private String ruc;
    private String address;
    private String clinicType;
    private String mobileSubtype;
    private Boolean hasVehicle;
    private String vehiclePlate;
    private String specialty;

    @ElementCollection
    @CollectionTable(name = "user_account_coverage_districts", joinColumns = @JoinColumn(name = "user_account_id"))
    @Column(name = "district")
    private List<String> coverageDistricts = new ArrayList<>();

    public UserAccount() {}

    public UserAccount(String username, String password, String email, String userType, String fullName, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.fullName = fullName;
        this.phone = phone;
    }

    public void updateProfile(String fullName, String email, String phone, String district) {
        if (fullName != null) this.fullName = fullName;
        if (email != null) this.email = email;
        if (phone != null) this.phone = phone;
        if (district != null) this.district = district;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getClinicName() { return clinicName; }
    public void setClinicName(String clinicName) { this.clinicName = clinicName; }
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getClinicType() { return clinicType; }
    public void setClinicType(String clinicType) { this.clinicType = clinicType; }
    public String getMobileSubtype() { return mobileSubtype; }
    public void setMobileSubtype(String mobileSubtype) { this.mobileSubtype = mobileSubtype; }
    public Boolean getHasVehicle() { return hasVehicle; }
    public void setHasVehicle(Boolean hasVehicle) { this.hasVehicle = hasVehicle; }
    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public List<String> getCoverageDistricts() { return coverageDistricts; }
    public void setCoverageDistricts(List<String> coverageDistricts) { this.coverageDistricts = coverageDistricts == null ? new ArrayList<>() : coverageDistricts; }
}
