package com.petcare.center.platform.contexts.iam.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record SignUpResource(
        @NotBlank String username,
        @NotBlank String password,
        @Email String email,
        @NotBlank String userType,
        @NotBlank String fullName,
        @NotBlank String phone,
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
