package com.petcare.center.platform.contexts.serviceproviders.interfaces.rest;

import com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories.ClinicRepository;
import com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories.MobileProfessionalRepository;
import com.petcare.center.platform.contexts.serviceproviders.interfaces.rest.resources.ServiceProviderResource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/service-providers")
public class ServiceProvidersController {
    private final ClinicRepository clinicRepository;
    private final MobileProfessionalRepository mobileProfessionalRepository;

    public ServiceProvidersController(ClinicRepository clinicRepository, MobileProfessionalRepository mobileProfessionalRepository) {
        this.clinicRepository = clinicRepository;
        this.mobileProfessionalRepository = mobileProfessionalRepository;
    }

    @GetMapping
    public List<ServiceProviderResource> search(@RequestParam(required = false) String district,
                                                @RequestParam(required = false) String specialty,
                                                @RequestParam(required = false) String type) {
        var providers = new ArrayList<ServiceProviderResource>();
        if (type == null || "clinic".equalsIgnoreCase(type)) {
            clinicRepository.findAll().stream()
                    .filter(c -> district == null || (c.getDistrict() != null && c.getDistrict().equalsIgnoreCase(district)))
                    .filter(c -> specialty == null || c.getSpecialties().stream().anyMatch(s -> s.toLowerCase().contains(specialty.toLowerCase())))
                    .map(c -> new ServiceProviderResource(c.getId(), "clinic", c.getName(), c.getEmail(), c.getPhone(), c.getAddress(), c.getDistrict(), c.getSpecialties(), c.getServicesOffered(), c.getRating(), c.getOpeningHours(), null, null, null, null))
                    .forEach(providers::add);
        }
        if (type == null || "mobile".equalsIgnoreCase(type)) {
            mobileProfessionalRepository.findAll().stream()
                    .filter(m -> district == null || m.getCoverageDistricts().stream().anyMatch(d -> d.equalsIgnoreCase(district)))
                    .filter(m -> specialty == null || (m.getSpecialty() != null && m.getSpecialty().toLowerCase().contains(specialty.toLowerCase())))
                    .map(m -> new ServiceProviderResource(m.getId(), "mobile", m.getName(), m.getEmail(), m.getPhone(), null, m.getCoverageDistricts().isEmpty() ? null : m.getCoverageDistricts().get(0), (m.getSpecialty() == null ? List.of() : List.of(m.getSpecialty())), List.of(), m.getRating(), null, m.getMobileSubtype(), m.getCoverageDistricts(), m.getHasVehicle(), m.getVehiclePlate()))
                    .forEach(providers::add);
        }
        return providers;
    }
}
