package com.patient.reservation.controller.user.representation;

import com.patient.core.assembler.BaseRepresentationModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Data
@Schema(name = "UserRepresentationModel")
@EqualsAndHashCode(callSuper = true)
@Relation(value = "user", collectionRelation = "users")
public class UserRepresentationModel extends BaseRepresentationModel<UserRepresentationModel> {

    @Schema(description = "Login username", example = "user1")
    private String username;

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

    @Schema(description = "True if user is a patient", example = "true")
    private Boolean isPatient;
}
