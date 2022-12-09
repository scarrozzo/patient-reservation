package com.patient.reservation.domain.visit.service;

import com.patient.reservation.domain.visit.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VisitDomainService {

    @Autowired
    private VisitRepository visitRepository;



}
