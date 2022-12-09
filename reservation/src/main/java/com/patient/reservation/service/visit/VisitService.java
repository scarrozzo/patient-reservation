package com.patient.reservation.service.visit;

import com.patient.reservation.domain.visit.service.VisitDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VisitService {

    @Autowired
    private VisitDomainService visitDomainService;


}
