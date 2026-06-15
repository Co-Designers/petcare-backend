package com.petcare.center.platform.contexts.iam.application.internal.commandservices;

import com.petcare.center.platform.contexts.clinicmanagement.domain.model.aggregates.Clinic;
import com.petcare.center.platform.contexts.clinicmanagement.infrastructure.persistence.jpa.repositories.ClinicRepository;
import com.petcare.center.platform.contexts.iam.domain.model.aggregates.UserAccount;
import com.petcare.center.platform.contexts.iam.domain.model.commands.SignInCommand;
import com.petcare.center.platform.contexts.iam.domain.model.commands.SignUpCommand;
import com.petcare.center.platform.contexts.iam.domain.services.UserCommandService;
import com.petcare.center.platform.contexts.iam.infrastructure.persistence.jpa.repositories.UserAccountRepository;
import com.petcare.center.platform.contexts.mobile.domain.model.aggregates.MobileProfessional;
import com.petcare.center.platform.contexts.mobile.infrastructure.persistence.jpa.repositories.MobileProfessionalRepository;
import com.petcare.center.platform.contexts.shared.domain.exceptions.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserAccountRepository userAccountRepository;
    private final ClinicRepository clinicRepository;
    private final MobileProfessionalRepository mobileProfessionalRepository;

    public UserCommandServiceImpl(UserAccountRepository userAccountRepository, ClinicRepository clinicRepository, MobileProfessionalRepository mobileProfessionalRepository) {
        this.userAccountRepository = userAccountRepository;
        this.clinicRepository = clinicRepository;
        this.mobileProfessionalRepository = mobileProfessionalRepository;
    }

    @Override
    public UserAccount handle(SignUpCommand command) {
        if (userAccountRepository.existsByUsername(command.username())) {
            throw new BusinessRuleViolationException("Username already exists");
        }
        if (userAccountRepository.existsByEmail(command.email())) {
            throw new BusinessRuleViolationException("Email already exists");
        }
        var user = new UserAccount(command.username(), command.password(), command.email(), command.userType(), command.fullName(), command.phone());
        user.setDistrict(command.district());
        user.setClinicName(command.clinicName());
        user.setRuc(command.ruc());
        user.setAddress(command.address());
        user.setClinicType(command.clinicType());
        user.setMobileSubtype(command.mobileSubtype());
        user.setCoverageDistricts(command.coverageDistricts());
        user.setHasVehicle(command.hasVehicle());
        user.setVehiclePlate(command.vehiclePlate());
        user.setSpecialty(command.specialty());
        var saved = userAccountRepository.save(user);

        if ("CLINIC".equalsIgnoreCase(saved.getUserType())) {
            var clinic = new Clinic();
            clinic.setName(command.clinicName() != null ? command.clinicName() : command.fullName());
            clinic.setEmail(command.email());
            clinic.setPhone(command.phone());
            clinic.setAddress(command.address());
            clinic.setDistrict(command.district());
            clinic.setRuc(command.ruc());
            clinic.setClinicType(command.clinicType());
            clinic.setDescription("Veterinary clinic registered in PetCare Platform.");
            clinic.setOpeningHours("Lun-Sáb 08:00-20:00");
            clinic.setServicesOffered(List.of("Consulta general", "Vacunación"));
            clinic.setSpecialties(List.of("Medicina general"));
            clinic.setRating(4.5);
            clinicRepository.save(clinic);
        }
        if ("MOBILE".equalsIgnoreCase(saved.getUserType())) {
            var mobile = new MobileProfessional();
            mobile.setUserId(saved.getId());
            mobile.setName(command.fullName());
            mobile.setEmail(command.email());
            mobile.setPhone(command.phone());
            mobile.setMobileSubtype(command.mobileSubtype());
            mobile.setCoverageDistricts(command.coverageDistricts());
            mobile.setHasVehicle(command.hasVehicle() != null && command.hasVehicle());
            mobile.setVehiclePlate(command.vehiclePlate());
            mobile.setSpecialty(command.specialty());
            mobile.setRating(4.5);
            mobile.setIsActive(true);
            mobileProfessionalRepository.save(mobile);
        }
        return saved;
    }

    @Override
    public UserAccount handle(SignInCommand command) {
        var user = userAccountRepository.findByUsername(command.username())
                .orElseThrow(() -> new BusinessRuleViolationException("Invalid credentials"));
        if (!user.getPassword().equals(command.password())) {
            throw new BusinessRuleViolationException("Invalid credentials");
        }
        return user;
    }
}
