package com.patient.reservation.domain.visit.repository;

import com.patient.core.repository.BaseRepository;
import com.patient.reservation.domain.visit.model.Visit;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitRepository extends BaseRepository<Visit, Long> {
    Optional<Visit> findByUidAndDoctorId(String uid, Long doctorId);
}
