package com.patient.reservation.domain.visit.model;

import com.patient.core.model.BaseEntity;
import com.patient.reservation.domain.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "visit")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Visit extends BaseEntity {

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private ZonedDateTime date;

    @Enumerated(STRING)
    @Column(name = "type", length = 100)
    private VisitType visitType;

    @Enumerated(STRING)
    @Column(name = "reason", length = 100)
    private VisitReason visitReason;

    @Lob
    @Column(length = 16777215)
    private String familyHistory;

    @Column
    private Long doctorId;

    @Column
    private Long patientId;

    @Setter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "id", insertable = false, updatable = false)
    private User patient;

    @Setter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId", referencedColumnName = "id", insertable = false, updatable = false)
    private User doctor;

    @PrePersist
    private void initUid() {
        this.uid = UUID.randomUUID().toString();
    }

}
