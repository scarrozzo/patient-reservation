package com.patient.reservation.domain.user.model;

import com.patient.core.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class User extends BaseEntity {

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Column(length = 50)
    private String username;

    @Column(length = 60)
    private String passwordHash;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private Boolean loginEnabled;

    @Column
    private Boolean isDoctor;

    @Column
    private Boolean isPatient;

}
