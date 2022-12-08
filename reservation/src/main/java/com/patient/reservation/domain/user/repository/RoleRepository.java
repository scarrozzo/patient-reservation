package com.patient.reservation.domain.user.repository;

import com.patient.core.repository.BaseRepository;
import com.patient.reservation.domain.user.model.Role;
import com.patient.reservation.domain.user.model.RoleType;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String name);

    Set<Role> findByNameIn(Set<RoleType> name);

}
