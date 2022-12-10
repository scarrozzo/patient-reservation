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
import java.util.Optional;

@Schema(name = "PatchVisit")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class PatchVisitDto {

    @Schema(description = "Visit date time", example = "2022-12-11T16:00+01:00[Europe/Rome]")
    private Optional<@NotNull ZonedDateTime> date;

    @Schema(description = "Visit type", example = "DOCTOR_OFFICE")
    private Optional<@NotNull VisitType> visitType;

    @Schema(description = "Visit reason", example = "RECURRING_VISIT")
    private Optional<@NotNull VisitReason> visitReason;

    @Schema(description = "Family history", example = "It's lupus")
    private Optional<@Size(min = 0, max = 10000) String> familyHistory;

    @Schema(description = "Patient uid", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
    private Optional<@NotNull @Size(min = 36, max = 36) String> patientUid;

}
