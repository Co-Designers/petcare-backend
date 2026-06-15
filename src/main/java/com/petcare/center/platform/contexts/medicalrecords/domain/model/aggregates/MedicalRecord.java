package com.petcare.center.platform.contexts.medicalrecords.domain.model.aggregates;

import com.petcare.center.platform.contexts.shared.infrastructure.persistence.jpa.model.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medical_records")
public class MedicalRecord extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false)
    private Long petId;
    private Long appointmentId;
    @Column(nullable = false)
    private Instant date;
    private String veterinarianName;
    private String clinicName;
    @Column(length = 1000)
    private String diagnosis;
    @Column(length = 1000)
    private String treatment;
    private String medications;
    @Column(length = 1000)
    private String observations;
    private String vaccinationName;
    private LocalDate nextDueDate;

    @ElementCollection
    @CollectionTable(name = "medical_record_document_urls", joinColumns = @JoinColumn(name = "medical_record_id"))
    @Column(name = "document_url")
    private List<String> documentUrls = new ArrayList<>();

    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public Instant getDate() { return date; }
    public void setDate(Instant date) { this.date = date; }
    public String getVeterinarianName() { return veterinarianName; }
    public void setVeterinarianName(String veterinarianName) { this.veterinarianName = veterinarianName; }
    public String getClinicName() { return clinicName; }
    public void setClinicName(String clinicName) { this.clinicName = clinicName; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
    public String getVaccinationName() { return vaccinationName; }
    public void setVaccinationName(String vaccinationName) { this.vaccinationName = vaccinationName; }
    public LocalDate getNextDueDate() { return nextDueDate; }
    public void setNextDueDate(LocalDate nextDueDate) { this.nextDueDate = nextDueDate; }
    public List<String> getDocumentUrls() { return documentUrls; }
    public void setDocumentUrls(List<String> documentUrls) { this.documentUrls = documentUrls == null ? new ArrayList<>() : documentUrls; }
}
