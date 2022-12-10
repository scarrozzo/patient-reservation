package com.patient.reservation.command.visit.util;

import com.patient.reservation.controller.visit.VisitSearchParameters;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import com.patient.reservation.exception.ServiceError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Component
public class VisitValidator {

    public static final int MINIMUM_VISIT_TIME = 29;

    @Autowired
    private VisitDomainService visitDomainService;

    public void checkIsFutureDate(ZonedDateTime date){
        // now should be less than or equals to visit date
        if(ZonedDateTime.now().compareTo(date) > 0){
            log.error("Now: {}. Invalid visit date: {}", ZonedDateTime.now(), date);
            throw new IllegalArgumentException(ServiceError.E0011.getMessage());
        }
    }

    public void checkOverlappingVisits(ZonedDateTime date) {
        Page<Visit> overlappingVisits = getOverlappingVisits(date);

        if(!overlappingVisits.getContent().isEmpty()){
            log.error(ServiceError.E0012.getMessage());
            log.debug("Overlapping visits: {}", overlappingVisits);
            throw new IllegalArgumentException(ServiceError.E0012.getMessage());
        }
    }

    public void checkOverlappingVisitsExcludingCurrentVisit(ZonedDateTime date, String currentVisitUid) {
        Page<Visit> overlappingVisits = getOverlappingVisits(date);

        if(!overlappingVisits.getContent().stream().filter(v -> !v.getUid().equals(currentVisitUid)).toList().isEmpty()){
            log.error(ServiceError.E0012.getMessage());
            log.debug("Overlapping visits: {}", overlappingVisits);
            throw new IllegalArgumentException(ServiceError.E0012.getMessage());
        }
    }

    private Page<Visit> getOverlappingVisits(ZonedDateTime date) {
        // There should be no other visits scheduled half an hour before and half an hour after the given date
        // Refactor: Add the constant as a configurable property of the doctor
        return visitDomainService.getVisits(VisitSearchParameters.builder()
                .startDate(date.minusMinutes(MINIMUM_VISIT_TIME))
                .endDate(date.plusMinutes(MINIMUM_VISIT_TIME))
                .build(), Pageable.ofSize(100));
    }
}
