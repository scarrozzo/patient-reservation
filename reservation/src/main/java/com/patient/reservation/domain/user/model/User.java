package com.patient.reservation.domain.user.model;

import com.patient.core.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    private void initUid() {
        this.uid = UUID.nameUUIDFromBytes((getClass().getSimpleName() + "_" + getUsername()).getBytes()).toString();
    }
}
