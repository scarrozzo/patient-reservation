package com.patient.reservation.domain.user.dto;

import com.patient.reservation.domain.user.model.PatientIdentifierType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Optional;

@Schema(name = "PatchUser")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class PatchUserDto {

    @Schema(description = "Patient identifier", example = "432-79-0825")
    private Optional<@NotBlank @Size(max = 100) String> patientIdentifier;

    @Schema(description = "Patient identifier type", example = "SOCIAL_SECURITY_NUMBER")
    private Optional<@NotNull PatientIdentifierType> patientIdentifierType;

    @Schema(description = "User first name", example = "Sergio")
    private Optional<@NotBlank @Size(max = 50) String> firstName;

    @Schema(description = "User last name", example = "Carrozzo")
    private Optional<@NotBlank @Size(max = 50) String> lastName;

    @Schema(description = "Date of birth", example = "24/10/1990")
    private Optional<@NotNull LocalDate> dateOfBirth;

    @Schema(description = "Can login", example = "true")
    private Optional<Boolean> loginEnabled;

}
