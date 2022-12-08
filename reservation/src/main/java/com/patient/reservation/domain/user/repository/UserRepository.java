package com.patient.reservation.domain.user.repository;

import com.patient.core.repository.BaseRepository;
import com.patient.reservation.domain.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
