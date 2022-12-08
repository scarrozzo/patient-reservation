package com.patient.reservation.domain.user.model;

import com.patient.core.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Role extends BaseEntity {

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private RoleType name;

}
