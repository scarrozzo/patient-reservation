package com.patient.reservation.command.visit;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.command.visit.util.VisitValidator;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
import com.patient.reservation.domain.visit.dto.PatchVisitDto;
import com.patient.reservation.domain.visit.factory.VisitDtoMapper;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Scope("prototype")
@Component
public class UpdateVisitCommand extends BaseTransactionalCommand<Visit> {

    @Autowired
    private VisitDomainService visitDomainService;

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private VisitValidator visitValidator;

    @Autowired
    private VisitDtoMapper visitDtoMapper;

    private final PatchVisitDto dto;
    private Visit visit;

    public UpdateVisitCommand(Visit visit, PatchVisitDto dto){
        this.dto = dto;
        this.visit = visit;
    }

    @Override
    public Visit doExecute(){
        if(dto.getDate() != null && dto.getDate().isPresent()) {
            log.info("Checking if visit date is valid...");
            visitValidator.checkIsFutureDate(dto.getDate().get());
            visitValidator.checkOverlappingVisitsExcludingCurrentVisit(dto.getDate().get(), visit.getUid());
            log.info("{} is valid", dto.getDate());
        }

        User patient = null;
        if(dto.getPatientUid() != null && dto.getPatientUid().isPresent()){
            patient = userDomainService.getPatient(dto.getPatientUid().get());
        }

        log.info("Updating {} with {}...", visit, dto);
        visitDtoMapper.patch(visit, dto, patient);
        visit = visitDomainService.save(visit);
        log.info("{} updated", visit);

        return visit;
    }

}
