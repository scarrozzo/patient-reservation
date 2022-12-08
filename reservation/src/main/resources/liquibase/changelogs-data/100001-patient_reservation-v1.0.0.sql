-- liquibase formatted sql

-- changeset 100001-patient-reservation:1
INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash, first_name,
                                      last_name, date_of_birth, login_enabled, is_patient, is_doctor)
VALUES (1, 0, '665a7f14-38ad-4662-8ff3-0beebbdd55e8', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'admin', '12345', 'Admin', 'Admin', '1990-10-24', 1, 0, 0);
