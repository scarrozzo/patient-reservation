package com.patient.reservation.domain.visit.factory;

import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.visit.dto.PostVisitDto;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class VisitFactory {

    @Autowired
    private VisitDtoMapper visitDtoMapper;

    public Visit createVisit(PostVisitDto dto, User patient){
        Visit visit = new Visit();

        visit = visitDtoMapper.map(visit, dto);

        visit.setPatientId(patient.getId());
        visit.setDoctorId(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

        return visit;
    }

}
