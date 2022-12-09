package com.patient.reservation.domain.user.factory;

import com.patient.reservation.domain.user.dto.PatchUserDto;
import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User map(User entity, PostUserDto dto){

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setIsDoctor(dto.getIsDoctor());
        entity.setIsPatient(dto.getIsPatient());
        entity.setLoginEnabled(dto.getLoginEnabled());
        entity.setPatientIdentifier(dto.getPatientIdentifier());
        entity.setPatientIdentifierType(dto.getPatientIdentifierType());
        // persist encoded password
        entity.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        return entity;
    }

    public User patch(User entity, PatchUserDto dto){

        if(dto.getPatientIdentifier() != null){
            if(dto.getPatientIdentifier().isPresent()){
                entity.setPatientIdentifier(dto.getPatientIdentifier().get());
            } else {
                entity.setPatientIdentifier(null);
            }
        }

        if(dto.getPatientIdentifierType() != null){
            if(dto.getPatientIdentifierType().isPresent()){
                entity.setPatientIdentifierType(dto.getPatientIdentifierType().get());
            } else {
                entity.setPatientIdentifierType(null);
            }
        }

        if(dto.getFirstName() != null){
            if(dto.getFirstName().isPresent()){
                entity.setFirstName(dto.getFirstName().get());
            } else {
                entity.setFirstName(null);
            }
        }

        if(dto.getLastName() != null){
            if(dto.getLastName().isPresent()){
                entity.setLastName(dto.getLastName().get());
            } else {
                entity.setLastName(null);
            }
        }

        if(dto.getLoginEnabled() != null){
            if(dto.getLoginEnabled().isPresent()){
                entity.setLoginEnabled(dto.getLoginEnabled().get());
            } else {
                entity.setLoginEnabled(null);
            }
        }

        if(dto.getDateOfBirth() != null){
            if(dto.getDateOfBirth().isPresent()){
                entity.setDateOfBirth(dto.getDateOfBirth().get());
            } else {
                entity.setDateOfBirth(null);
            }
        }

        return entity;
    }

}
