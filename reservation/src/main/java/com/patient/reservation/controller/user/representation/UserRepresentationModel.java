package com.patient.reservation.controller.user.representation;

import com.patient.core.assembler.BaseRepresentationModel;
import com.patient.reservation.domain.user.model.PatientIdentifierType;
import com.patient.reservation.domain.user.model.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.util.Set;

@Data
@Schema(name = "UserRepresentationModel")
@EqualsAndHashCode(callSuper = true)
@Relation(value = "user", collectionRelation = "users")
public class UserRepresentationModel extends BaseRepresentationModel<UserRepresentationModel> {

    @Schema(description = "Login username", example = "user1")
    private String username;

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

    @Schema(description = "Can login", example = "true")
    private Boolean loginEnabled;

    @Schema(description = "True if user is a doctor", example = "true")
    private Boolean isDoctor;

    @Schema(description = "Doctor uid", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
    private String doctorUid;

    @Schema(description = "Doctor first name", example = "Sergio")
    private String doctorFirstName;

    @Schema(description = "Doctor last name", example = "Sergio")
    private String doctorLastName;

    @Schema(description = "True if user is a patient", example = "true")
    private Boolean isPatient;

    @Schema(description = "User roles", example = "[ADMIN]")
    Set<RoleType> roles;
}
