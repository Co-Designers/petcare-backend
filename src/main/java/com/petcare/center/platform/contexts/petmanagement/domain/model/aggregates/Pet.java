package com.petcare.center.platform.contexts.petmanagement.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
public class Pet extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false)
    private Long ownerId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String species;
    private String breed;
    private LocalDate birthDate;
    private Double weight;
    private String allergies;
    private String photoUrl;

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public void updateFrom(Pet source) {
        if (source.ownerId != null) this.ownerId = source.ownerId;
        if (source.name != null) this.name = source.name;
        if (source.species != null) this.species = source.species;
        if (source.breed != null) this.breed = source.breed;
        if (source.birthDate != null) this.birthDate = source.birthDate;
        if (source.weight != null) this.weight = source.weight;
        if (source.allergies != null) this.allergies = source.allergies;
        if (source.photoUrl != null) this.photoUrl = source.photoUrl;
    }
}
