package com.patient.reservation.domain.user.service;

import com.patient.reservation.domain.user.model.Role;
import com.patient.reservation.domain.user.model.RoleType;
import com.patient.reservation.domain.user.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class RoleDomainService {

    @Autowired
    private RoleRepository roleRepository;

    public Set<Role> findByNameIn(Set<RoleType> roles){
        return roleRepository.findByNameIn(roles);
    }

}
