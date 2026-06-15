package com.petcare.center.platform.contexts.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(
        Long id,
        String username,
        String email,
        String userType,
        String fullName,
        String phone,
        String district,
        String clinicName,
        String ruc,
        String address,
        String clinicType,
        String mobileSubtype,
        List<String> coverageDistricts,
        Boolean hasVehicle,
        String vehiclePlate,
        String specialty
) {}
