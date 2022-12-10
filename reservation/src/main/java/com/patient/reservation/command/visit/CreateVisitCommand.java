package com.patient.reservation.command.visit;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.controller.visit.VisitSearchParameters;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
import com.patient.reservation.domain.visit.dto.PostVisitDto;
import com.patient.reservation.domain.visit.factory.VisitFactory;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import com.patient.reservation.exception.ServiceError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Scope("prototype")
@Component
public class CreateVisitCommand extends BaseTransactionalCommand<Visit> {

    public static final int MINIMUM_VISIT_TIME = 29;

    @Autowired
    private VisitDomainService visitDomainService;

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private VisitFactory visitFactory;

    private final PostVisitDto dto;

    public CreateVisitCommand(PostVisitDto dto){
        this.dto = dto;
    }

    @Override
    public Visit doExecute(){
        checkVisitDateValidity(dto);

        log.info("Creating new visit...");
        User patient = userDomainService.getPatient(dto.getPatientUid());
        Visit visit = visitFactory.createVisit(dto, patient);
        visit = visitDomainService.save(visit);
        log.info("{} created", visit);

        return visit;
    }

    private void checkVisitDateValidity(PostVisitDto dto) {
        // now should be less than or equals to visit date
        if(ZonedDateTime.now().compareTo(dto.getDate()) > 0){
            log.error("Now: {}. Invalid visit date: {}", ZonedDateTime.now(), dto.getDate());
            throw new IllegalArgumentException(ServiceError.E0011.getMessage());
        }

        // There should be no other visits scheduled half an hour before and half an hour after the given date
        // Refactor: Add the constant as a configurable property of the doctor
        Page<Visit> overlappingVisits = visitDomainService.getVisits(VisitSearchParameters.builder()
                .startDate(dto.getDate().minusMinutes(MINIMUM_VISIT_TIME))
                .endDate(dto.getDate().plusMinutes(MINIMUM_VISIT_TIME))
                .build(), Pageable.ofSize(100));

        if(!overlappingVisits.getContent().isEmpty()){
            log.error(ServiceError.E0012.getMessage());
            log.debug("Overlapping visits: {}", overlappingVisits);
            throw new IllegalArgumentException(ServiceError.E0012.getMessage());
        }
    }

}
