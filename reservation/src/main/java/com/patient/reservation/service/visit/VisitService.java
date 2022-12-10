package com.patient.reservation.service.visit;

import com.patient.reservation.command.visit.CreateVisitCommand;
import com.patient.reservation.controller.visit.VisitSearchParameters;
import com.patient.reservation.domain.visit.dto.PostVisitDto;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VisitService {

    @Autowired
    private VisitDomainService visitDomainService;

    @Autowired
    private BeanFactory beanFactory;

    public Page<Visit> getVisits(VisitSearchParameters searchParameters, Pageable pageable){
        return visitDomainService.getVisits(searchParameters, pageable);
    }

    public Visit createVisit(PostVisitDto postVisitDto){
        CreateVisitCommand command = beanFactory.getBean(CreateVisitCommand.class, postVisitDto);
        return command.execute();
    }
}
