package com.patient.reservation.domain.visit.dto;

import com.patient.reservation.domain.visit.model.VisitReason;
import com.patient.reservation.domain.visit.model.VisitType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Schema(name = "PostVisit")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class PostVisitDto {

    @Schema(description = "Visit date time", example = "2022-12-11T16:00+01:00[Europe/Rome]")
    @NotNull
    private ZonedDateTime date;

    @Schema(description = "Visit type", example = "DOCTOR_OFFICE")
    @NotNull
    private VisitType visitType;

    @Schema(description = "Visit reason", example = "RECURRING_VISIT")
    @NotNull
    private VisitReason visitReason;

    @Schema(description = "Family history", example = "It's lupus")
    @Size(min = 0, max = 10000)
    private String familyHistory;

    @Schema(description = "Patient uid", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
    @Size(min = 36, max = 36)
    @NotNull
    private String patientUid;

}
