package com.patient.reservation.controller.visit.assembler;

import com.patient.core.assembler.BaseRepresentationModelAssemblerSupport;
import com.patient.reservation.controller.user.assembler.UserRepresentationModelAssembler;
import com.patient.reservation.controller.visit.VisitController;
import com.patient.reservation.controller.visit.representation.VisitRepresentationModel;
import com.patient.reservation.domain.visit.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VisitRepresentationModelAssembler extends BaseRepresentationModelAssemblerSupport<Visit, VisitRepresentationModel> {

    @Autowired
    private UserRepresentationModelAssembler userRepresentationModelAssembler;

    public VisitRepresentationModelAssembler(){
        super(VisitController.class, VisitRepresentationModel.class);
    }

    @Override
    public VisitRepresentationModel toModel(Visit entity) {
        return this.instantiateModel(entity);
    }

    @Override
    protected VisitRepresentationModel instantiateModel(Visit entity) {
        VisitRepresentationModel visitRepresentationModel = new VisitRepresentationModel();

        visitRepresentationModel.setUid(entity.getUid());
        visitRepresentationModel.setCreatedDate(entity.getCreatedDate());
        visitRepresentationModel.setCreatedTime(entity.getCreatedTime());
        visitRepresentationModel.setLastModifiedDate(entity.getLastModifiedDate());
        visitRepresentationModel.setLastModifiedTime(entity.getLastModifiedTime());
        visitRepresentationModel.setDate(entity.getDate());
        visitRepresentationModel.setVisitType(entity.getVisitType());
        visitRepresentationModel.setVisitReason(entity.getVisitReason());
        visitRepresentationModel.setFamilyHistory(entity.getFamilyHistory());

        if(entity.getDoctor() != null) {
            visitRepresentationModel.setDoctorUid(entity.getDoctor().getUid());
            visitRepresentationModel.setDoctorFirstName(entity.getDoctor().getFirstName());
            visitRepresentationModel.setDoctorLastName(entity.getDoctor().getLastName());
        }

        if(entity.getPatient() != null){
            visitRepresentationModel.setPatientUid(entity.getPatient().getUid());
            visitRepresentationModel.setPatientFirstName(entity.getPatient().getFirstName());
            visitRepresentationModel.setPatientLastName(entity.getPatient().getLastName());
        }

        return visitRepresentationModel;
    }
}
