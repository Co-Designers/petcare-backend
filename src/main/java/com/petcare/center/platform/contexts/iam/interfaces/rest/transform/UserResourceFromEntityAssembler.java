package com.petcare.center.platform.contexts.iam.interfaces.rest.transform;

import com.petcare.center.platform.contexts.iam.domain.model.aggregates.UserAccount;
import com.petcare.center.platform.contexts.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(UserAccount user) {
        return new UserResource(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getUserType(),
                user.getFullName(),
                user.getPhone(),
                user.getDistrict(),
                user.getClinicName(),
                user.getRuc(),
                user.getAddress(),
                user.getClinicType(),
                user.getMobileSubtype(),
                user.getCoverageDistricts(),
                user.getHasVehicle(),
                user.getVehiclePlate(),
                user.getSpecialty()
        );
    }
}
