package com.patient.reservation.domain.user.repository;

import com.patient.core.repository.BaseRepository;
import com.patient.reservation.domain.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
