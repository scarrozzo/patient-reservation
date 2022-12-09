package com.patient.reservation.domain.user.repository;

import com.patient.core.repository.BaseRepository;
import com.patient.reservation.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

//    @Query("select patient from User patient where patient.isPatient = true")
//    Page<User> findPatients(Pageable pageable);
}
