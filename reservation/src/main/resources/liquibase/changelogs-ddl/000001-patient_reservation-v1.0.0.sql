-- liquibase formatted sql

-- changeset 000001-patient-reservation:1
CREATE TABLE user
(
    id                 BIGINT      NOT NULL AUTO_INCREMENT,
    version_           BIGINT      NOT NULL DEFAULT 0,
    uid                VARCHAR(36) NOT NULL,
    creator            VARCHAR(96) NOT NULL,
    modifier           VARCHAR(96) NOT NULL,
    created_date       DATE        NOT NULL DEFAULT (CURRENT_DATE),
    created_time       TIME        NOT NULL DEFAULT (CURRENT_TIME),
    last_modified_date DATE        NOT NULL DEFAULT (CURRENT_DATE),
    last_modified_time TIME        NOT NULL DEFAULT (CURRENT_TIME),
    username           VARCHAR(50) NOT NULL,
    password_hash      VARCHAR(60) NOT NULL,
    first_name         VARCHAR(50) NOT NULL,
    last_name          VARCHAR(50) NOT NULL,
    date_of_birth      DATE        NOT NULL,
    login_enabled      BOOLEAN     NOT NULL DEFAULT FALSE,
    is_patient         BOOLEAN     NOT NULL,
    is_doctor          BOOLEAN     NOT NULL,
    PRIMARY KEY (id)
);

-- changeset 000001-patient-reservation:2
CREATE TABLE visit
(
    id                 BIGINT      NOT NULL AUTO_INCREMENT,
    version_           BIGINT      NOT NULL DEFAULT 0,
    uid                VARCHAR(36) NOT NULL,
    creator            VARCHAR(96) NOT NULL,
    modifier           VARCHAR(96) NOT NULL,
    created_date       DATE        NOT NULL DEFAULT (CURRENT_DATE),
    created_time       TIME        NOT NULL DEFAULT (CURRENT_TIME),
    last_modified_date DATE        NOT NULL DEFAULT (CURRENT_DATE),
    last_modified_time TIME        NOT NULL DEFAULT (CURRENT_TIME),
    date               TIMESTAMP   NOT NULL,
    type               VARCHAR(100),
    reason             VARCHAR(100),
    family_history     MEDIUMTEXT,
    doctor_id          BIGINT      NOT NULL,
    patient_id         BIGINT      NOT NULL,
    CONSTRAINT visit_doctor_id FOREIGN KEY (doctor_id) REFERENCES user (id),
    CONSTRAINT visit_patient_id FOREIGN KEY (patient_id) REFERENCES user (id),
    PRIMARY KEY (id)
);