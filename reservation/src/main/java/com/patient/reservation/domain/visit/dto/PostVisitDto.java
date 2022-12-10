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

    // TODO Schema

    @NotNull
    private ZonedDateTime date;

    @NotNull
    private VisitType visitType;

    @NotNull
    private VisitReason visitReason;

    @Size(min = 0, max = 10000)
    private String familyHistory;

    @Size(min = 36, max = 36)
    @NotNull
    private String patientUid;

}
