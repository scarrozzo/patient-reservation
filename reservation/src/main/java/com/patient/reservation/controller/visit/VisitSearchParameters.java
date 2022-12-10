package com.patient.reservation.controller.visit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class VisitSearchParameters {

    @Schema(description = "Search start date", example = "2022-12-11T16:00+01:00[Europe/Rome]")
    @NotNull
    private ZonedDateTime startDate;

    @Schema(description = "Search end date", example = "2022-12-25T16:00+01:00[Europe/Rome]")
    private ZonedDateTime endDate;

    @Schema(description = "Patient uid", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
    private String patientUid;

}
