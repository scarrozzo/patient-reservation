package com.patient.reservation.command.visit;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Scope("prototype")
@Component
public class DeleteVisitCommand extends BaseTransactionalCommand<Void> {

    @Autowired
    private VisitDomainService visitDomainService;

    private final Visit visit;

    public DeleteVisitCommand(Visit visit){
        this.visit = visit;
    }

    @Override
    public Void doExecute(){
        log.info("Deleting {}...", visit);
        visitDomainService.delete(visit);
        log.info("{} deleted.", visit);

        return null;
    }
}
