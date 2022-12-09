package com.patient.core.model;

import jakarta.persistence.*;
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
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@NaturalIdCache
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Setter
    @Getter
    @Version
    @Column(name = "version_")
    protected Long version;

    @ToString.Include
    @Setter
    @Getter
    @NaturalId(mutable = true)
    @NotNull
    @Column(unique = true, updatable = false, length = 36, nullable = false)
    protected String uid;

    @NotNull
    @CreatedBy
    @Column(name = "creator", length = 100)
    protected String creator;

    @NotNull
    @LastModifiedBy
    @Column(name = "modifier", length = 100)
    protected String modifier;

    @NotNull
    @CreatedDate
    @Column(name = "created_date")
    protected LocalDate createdDate;

    @NotNull
    @CreatedDate
    @Column(name = "created_time")
    protected LocalTime createdTime;

    @NotNull
    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDate lastModifiedDate;

    @NotNull
    @LastModifiedDate
    @Column(name = "last_modified_time")
    protected LocalTime lastModifiedTime;

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
