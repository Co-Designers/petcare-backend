package com.petcare.center.platform.contexts.serviceproviders.interfaces.rest.resources;

import java.util.List;

public record ServiceProviderResource(
        Long id,
        String type,
        String name,
        String email,
        String phone,
        String address,
        String district,
        List<String> specialties,
        List<String> servicesOffered,
        Double rating,
        String openingHours,
        String mobileSubtype,
        List<String> coverageDistricts,
        Boolean hasVehicle,
        String vehiclePlate
) {}
