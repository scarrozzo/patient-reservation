package com.patient.reservation.domain.visit.service;

import com.patient.reservation.controller.visit.VisitSearchParameters;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class VisitDomainService {

    public static final String VISIT_DATE = "date";
    public static final String PATIENT_ID = "patientId";
    public static final String PATIENT = "patient";
    public static final String ID = "id";

    @Autowired
    private VisitRepository visitRepository;

    public Page<Visit> getVisits(VisitSearchParameters searchParameters, Pageable pageable){
        List<Specification<Visit>> visitSpecifications = buildVisitSpecifications(searchParameters);

        Iterator<Specification<Visit>> it = visitSpecifications.iterator();
        Specification<Visit> combinedSpecifications = Specification.where(it.next());
        while (it.hasNext()) {
            combinedSpecifications =  combinedSpecifications.and(it.next());
        }

        return visitRepository.findAll(combinedSpecifications, pageable);
    }

    private List<Specification<Visit>> buildVisitSpecifications(VisitSearchParameters searchParameters){
        List<Specification<Visit>> specifications = new ArrayList<>();

        if(searchParameters.getStartDate() != null){
            specifications.add((visit, q, cb) -> cb.greaterThanOrEqualTo(visit.get(VISIT_DATE), searchParameters.getStartDate()));
        }

        if(searchParameters.getEndDate() != null){
            specifications.add((visit, q, cb) -> cb.lessThanOrEqualTo(visit.get(VISIT_DATE), searchParameters.getEndDate()));
        }

        if(StringUtils.hasText(searchParameters.getPatientUid())){
            specifications.add((visit, q, cb) -> cb.equal(visit.join(PATIENT).get("uid"),searchParameters.getPatientUid()));
        }

        return specifications;
    }
}
