package com.patient.reservation.controller.user;

import com.patient.reservation.domain.user.model.PatientIdentifierType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientSearchParameters {

    @Schema(description = "Patient identifier", example = "432-79-0825")
    private String patientIdentifier;

    @Schema(description = "Patient identifier type", example = "SOCIAL_SECURITY_NUMBER")
    private PatientIdentifierType patientIdentifierType;

    @Schema(description = "User first name", example = "Sergio")
    private String firstName;

    @Schema(description = "User last name", example = "Carrozzo")
    private String lastName;

    @Schema(description = "Date of birth", example = "24/10/1990")
    private LocalDate dateOfBirth;

}
