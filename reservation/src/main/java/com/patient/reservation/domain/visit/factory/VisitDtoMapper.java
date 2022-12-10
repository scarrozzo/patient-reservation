package com.patient.reservation.domain.visit.factory;

import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.visit.dto.PatchVisitDto;
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

    public Visit patch(Visit entity, PatchVisitDto dto, User patient){
        if(patient != null){
            entity.setPatientId(patient.getId());
        }

        if(dto.getDate() != null && dto.getDate().isPresent()){
            entity.setDate(dto.getDate().get());
        }

        if(dto.getVisitReason() != null && dto.getVisitReason().isPresent()){
            entity.setVisitReason(dto.getVisitReason().get());
        }

        if(dto.getVisitType() != null && dto.getVisitType().isPresent()){
            entity.setVisitType(dto.getVisitType().get());
        }

        if(dto.getFamilyHistory() != null){
            if(dto.getFamilyHistory().isPresent()){
                entity.setFamilyHistory(dto.getFamilyHistory().get());
            } else {
                entity.setFamilyHistory(null);
            }
        }

        return entity;
    }

}
