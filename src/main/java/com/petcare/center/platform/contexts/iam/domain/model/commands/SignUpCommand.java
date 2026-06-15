package com.petcare.center.platform.contexts.iam.domain.model.commands;

import java.util.List;

public record SignUpCommand(
        String username,
        String password,
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
