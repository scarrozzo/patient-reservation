package com.patient.reservation.domain.visit.factory;

import com.patient.reservation.domain.visit.dto.PostVisitDto;
import com.patient.reservation.domain.visit.model.Visit;
import org.springframework.stereotype.Component;

@Component
public class VisitDtoMapper {

    public Visit map(Visit entity, PostVisitDto dto){

        entity.setDate(dto.getDate());
        entity.setVisitReason(dto.getVisitReason());
        entity.setVisitType(dto.getVisitType());
        entity.setFamilyHistory(dto.getFamilyHistory());

        return entity;
    }

}
