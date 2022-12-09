package com.patient.reservation.controller.user.assembler;

import com.patient.core.assembler.BaseRepresentationModelAssemblerSupport;
import com.patient.reservation.controller.user.UserController;
import com.patient.reservation.controller.user.representation.UserRepresentationModel;
import com.patient.reservation.domain.user.model.Role;
import com.patient.reservation.domain.user.model.RoleType;
import com.patient.reservation.domain.user.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRepresentationModelAssembler extends BaseRepresentationModelAssemblerSupport<User, UserRepresentationModel> {

    public UserRepresentationModelAssembler(){
        super(UserController.class, UserRepresentationModel.class);
    }

    @Override
    public UserRepresentationModel toModel(User entity) {
        return this.instantiateModel(entity);
    }

    @Override
    protected UserRepresentationModel instantiateModel(User entity) {
        UserRepresentationModel userRepresentationModel = new UserRepresentationModel();

        userRepresentationModel.setUid(entity.getUid());
        userRepresentationModel.setUsername(entity.getUsername());
        userRepresentationModel.setPatientIdentifier(entity.getPatientIdentifier());
        userRepresentationModel.setPatientIdentifierType(entity.getPatientIdentifierType());
        userRepresentationModel.setFirstName(entity.getFirstName());
        userRepresentationModel.setLastName(entity.getLastName());
        userRepresentationModel.setDateOfBirth(entity.getDateOfBirth());
        userRepresentationModel.setLoginEnabled(entity.getLoginEnabled());
        userRepresentationModel.setIsDoctor(entity.getIsDoctor());
        userRepresentationModel.setIsPatient(entity.getIsPatient());
        userRepresentationModel.setCreatedDate(entity.getCreatedDate());
        userRepresentationModel.setCreatedTime(entity.getCreatedTime());
        userRepresentationModel.setLastModifiedDate(entity.getLastModifiedDate());
        userRepresentationModel.setLastModifiedTime(entity.getLastModifiedTime());
        userRepresentationModel.setCreator(entity.getCreator());
        userRepresentationModel.setModifier(entity.getModifier());

        if(entity.getRoles() != null && !entity.getRoles().isEmpty()) {
            Set<RoleType> roles = new HashSet<>();
            for(Role role : entity.getRoles()) {
                roles.add(role.getName());
            }
            userRepresentationModel.setRoles(roles);
        }

        return userRepresentationModel;
    }

}
