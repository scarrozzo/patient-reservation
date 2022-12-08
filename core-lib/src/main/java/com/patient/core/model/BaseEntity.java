package com.patient.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@NaturalIdCache
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Setter
    @Getter
    @Version
    @Column(name = "version_")
    private Long version;

    @ToString.Include
    @Setter
    @Getter
    @NaturalId
    @NotNull
    @Column(unique = true, updatable = false, length = 64, nullable = false)
    private String uid;

    @NotNull
    @CreatedBy
    @Column(name = "creator", length = 96)
    private String creator;

    @NotNull
    @LastModifiedBy
    @Column(name = "modifier", length = 96)
    private String modifier;

    @NotNull
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdDate;

    @NotNull
    @CreatedDate
    @Column(name = "created_time")
    private LocalTime createdTime;

    @NotNull
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;

    @NotNull
    @LastModifiedDate
    @Column(name = "last_modified_time")
    private LocalTime lastModifiedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity entity = (BaseEntity) o;
        return getUid() != null && Objects.equals(getUid(), entity.getUid());
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object code = this.getUid();
        result = result * PRIME + (code == null ? 43 : code.hashCode());
        return result;
    }
}
