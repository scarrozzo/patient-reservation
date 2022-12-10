package com.patient.reservation.service.visit;

import com.patient.reservation.controller.visit.VisitSearchParameters;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VisitService {

    @Autowired
    private VisitDomainService visitDomainService;

    public Page<Visit> getVisits(VisitSearchParameters searchParameters, Pageable pageable){
        return visitDomainService.getVisits(searchParameters, pageable);
    }

}
