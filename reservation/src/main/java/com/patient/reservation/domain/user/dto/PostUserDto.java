package com.patient.reservation.domain.user.dto;

import com.patient.reservation.domain.user.model.PatientIdentifierType;
import com.patient.reservation.domain.user.model.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Schema(name = "PostUser")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class PostUserDto {

    @Schema(description = "Login username", example = "user1")
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    /**
     * Password must contain at least one digit [0-9].
     * Password must contain at least one lowercase Latin character [a-z].
     * Password must contain at least one uppercase Latin character [A-Z].
     * Password must contain at least one special character like ! @ # & ( ).
     * Password must contain a length of at least 8 characters and a maximum of 20 characters.
     */
    @Schema(description = "User password", example = "Password123?")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @Schema(description = "Patient identifier", example = "432-79-0825")
    @NotBlank
    @Size(max = 100)
    private String patientIdentifier;

    @Schema(description = "Patient identifier type", example = "SOCIAL_SECURITY_NUMBER")
    @NotNull
    private PatientIdentifierType patientIdentifierType;

    @Schema(description = "User first name", example = "Sergio")
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @Schema(description = "User last name", example = "Carrozzo")
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Schema(description = "Date of birth", example = "24/10/1990")
    @NotNull
    private LocalDate dateOfBirth;

    @Schema(description = "Can login", example = "true")
    @NotNull
    private Boolean loginEnabled;

    @Schema(description = "True if user is a doctor", example = "true")
    @NotNull
    private Boolean isDoctor;

    @Schema(description = "True if user is a patient", example = "true")
    @NotNull
    private Boolean isPatient;

    @Schema(description = "The associated doctor", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
    private String doctorUid;

    @Schema(description = "User roles", example = "[ADMIN]")
    Set<RoleType> roles;
}
