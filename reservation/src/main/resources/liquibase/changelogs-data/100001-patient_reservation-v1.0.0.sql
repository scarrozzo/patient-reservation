-- liquibase formatted sql

-- changeset 100001-patient-reservation:1
INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash, first_name,
                                      last_name, date_of_birth, login_enabled, is_patient, is_doctor)
VALUES (1, 0, '665a7f14-38ad-4662-8ff3-0beebbdd55e8', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'admin', '$2a$12$Ed9Q7.c1RSKLqf7aymHkNOej4KC/DwZ62V0PqWfBLxFz3M3apywWK', 'Admin', 'Admin',
        '1990-10-24', 1, 0, 0);
INSERT INTO patient_reservation.role(id, version_, uid, creator, modifier, created_date, created_time,
                                     last_modified_date, last_modified_time, name)
VALUES (1, 0, 'dfb93c7d-1ee6-4790-8d58-5cb47889c2c6', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'ADMIN');
INSERT INTO patient_reservation.role(id, version_, uid, creator, modifier, created_date, created_time,
                                     last_modified_date, last_modified_time, name)
VALUES (2, 0, 'ae5e08f9-31f1-476e-8533-35f7db67375c', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'DOCTOR');
INSERT INTO patient_reservation.role(id, version_, uid, creator, modifier, created_date, created_time,
                                     last_modified_date, last_modified_time, name)
VALUES (3, 0, 'f78f1569-30af-4dab-affb-feecf4cdcc62', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'PATIENT');
INSERT INTO patient_reservation.user_role(user_id, role_id)
VALUES (1, 1);
