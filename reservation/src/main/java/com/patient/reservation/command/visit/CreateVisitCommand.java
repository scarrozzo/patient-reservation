package com.patient.reservation.command.visit;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.command.visit.util.VisitValidator;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
import com.patient.reservation.domain.visit.dto.PostVisitDto;
import com.patient.reservation.domain.visit.factory.VisitFactory;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Scope("prototype")
@Component
public class CreateVisitCommand extends BaseTransactionalCommand<Visit> {

    @Autowired
    private VisitDomainService visitDomainService;

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private VisitFactory visitFactory;

    @Autowired
    private VisitValidator visitValidator;

    private final PostVisitDto dto;

    public CreateVisitCommand(PostVisitDto dto){
        this.dto = dto;
    }

    @Override
    public Visit doExecute(){
        log.info("Checking if visit date is valid...");
        visitValidator.checkIsFutureDate(dto.getDate());
        visitValidator.checkOverlappingVisits(dto.getDate());
        log.info("{} is valid", dto.getDate());

        log.info("Creating new visit...");
        User patient = userDomainService.getPatient(dto.getPatientUid());
        Visit visit = visitFactory.createVisit(dto, patient);
        visit = visitDomainService.save(visit);
        log.info("{} created", visit);

        return visit;
    }

}
