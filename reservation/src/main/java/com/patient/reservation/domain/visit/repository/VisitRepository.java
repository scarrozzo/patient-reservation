package com.patient.reservation.domain.visit.repository;

import com.patient.core.repository.BaseRepository;
import com.patient.reservation.domain.visit.model.Visit;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends BaseRepository<Visit, Long> {
}
