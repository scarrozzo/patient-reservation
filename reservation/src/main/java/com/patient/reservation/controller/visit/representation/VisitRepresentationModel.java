package com.patient.reservation.controller.visit.representation;

import com.patient.core.assembler.BaseRepresentationModel;
import com.patient.reservation.domain.visit.model.VisitReason;
import com.patient.reservation.domain.visit.model.VisitType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.server.core.Relation;

import java.time.ZonedDateTime;

@Data
@Schema(name = "VisitRepresentationModel")
@EqualsAndHashCode(callSuper = true)
@Relation(value = "visit", collectionRelation = "visits")
public class VisitRepresentationModel extends BaseRepresentationModel<VisitRepresentationModel> {

    @Schema(description = "The visit date", example = "2022-01-01T19:20:00Z")
    private ZonedDateTime date;

    @Schema(description = "The visit type", example = "DOCTOR_OFFICE")
    private VisitType visitType;

    @Schema(description = "The visit reason", example = "FIRST_VISIT")
    private VisitReason visitReason;

    @Schema(description = "The family history", example = "Free text section.")
    private String familyHistory;

    @Schema(description = "Patient uid", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
    private String patientUid;

    @Schema(description = "Patient first name", example = "Sergio")
    private String patientFirstName;

    @Schema(description = "Patient last name", example = "Carrozzo")
    private String patientLastName;

    @Schema(description = "Doctor uid", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
    private String doctorUid;

    @Schema(description = "Doctor first name", example = "Gregory")
    private String doctorFirstName;

    @Schema(description = "Doctor last name", example = "House")
    private String doctorLastName;

}
