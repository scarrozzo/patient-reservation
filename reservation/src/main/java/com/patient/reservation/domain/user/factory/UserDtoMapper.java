package com.patient.reservation.domain.user.factory;

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

}
