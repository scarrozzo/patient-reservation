-- liquibase formatted sql

-- changeset 100001-patient-reservation:1
INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier,
                                      patient_identifier_type, first_name, last_name, date_of_birth, login_enabled,
                                      is_patient, is_doctor, doctor_id)
VALUES (1, 0, '665a7f14-38ad-4662-8ff3-0beebbdd55e8', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'admin', '$2a$12$Ed9Q7.c1RSKLqf7aymHkNOej4KC/DwZ62V0PqWfBLxFz3M3apywWK', '432-79-0825',
        'SOCIAL_SECURITY_NUMBER', 'Admin', 'Admin',
        '1990-10-24', 1, 0, 0, null);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (2, 0, '5b286c35-73c5-39ab-980d-e24fbd4b63e1', 'Liquibase', 'Liquibase', '2022-12-09', '16:24:36', '2022-12-09',
        '16:24:36', 'drhouse', '$2a$10$gk4WgUsNHcqftvY.l4ENU.K4lckPtuWWbKvdGc1gk7cKv6QlCwl/W', '423-80-1236',
        'SOCIAL_SECURITY_NUMBER', 'Gregory', 'House', '1980-10-10', 1, 0, 1, null);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (3, 0, '20ab2424-492c-3c05-9bd6-46e4af8adb25', 'Liquibase', 'Liquibase', '2022-12-09', '17:35:50', '2022-12-09',
        '17:35:50', 'patient1', '$2a$10$TVMpM9TLKc/u2KVaz/pFguRotYPg/rAmhMY288AVUlLIWAF/MFoo6', '500-80-1111',
        'SOCIAL_SECURITY_NUMBER', 'Mario', 'Bianchi', '1990-11-01', 1, 1, 0, 2);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (4, 0, 'a4c09e1f-9d0e-3337-8037-f22264998588', 'Liquibase', 'Liquibase', '2022-12-09', '17:36:25', '2022-12-09',
        '17:36:25', 'patient2', '$2a$10$U5K22nX4vdV7Ogh.S9AfN.EfCicSkiELBJXkcU3PyCu2OC/KDL4Qa', '500-80-1112',
        'SOCIAL_SECURITY_NUMBER', 'Marco', 'Verdi', '1995-03-22', 0, 1, 0, 2);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (5, 0, 'bf5aa05e-9f00-3e96-b4a5-a61ca9b9105c', 'Liquibase', 'Liquibase', '2022-12-09', '17:36:56', '2022-12-09',
        '17:36:56', 'patient3', '$2a$10$zGDQSP/Y4h/6AoEmibyF1.iN75LFmxiPqKL5Fqs4pwcPiC6mhulsq', '500-80-1113',
        'SOCIAL_SECURITY_NUMBER', 'Giuseppe', 'Neri', '1997-04-15', 1, 1, 0, 2);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (6, 0, 'c2ba5731-d620-39c1-947e-de275f424062', 'Liquibase', 'Liquibase', '2022-12-09', '17:37:23', '2022-12-09',
        '17:37:23', 'patient4', '$2a$10$mc4cG6fbCCXYG7YYudoe3.3L8T29LZb5uUbby2vk.6cylYWuLhblO', '500-80-1114',
        'SOCIAL_SECURITY_NUMBER', 'Rossana', 'Rossi', '2000-06-29', 1, 1, 0, 2);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (7, 0, '66223615-5ff3-3b56-ac5f-bcd6de90caf9', 'Liquibase', 'Liquibase', '2022-12-09', '17:37:45', '2022-12-09',
        '17:37:45', 'patient5', '$2a$10$Y4WQyLduINC5L82RD9crqOzlAai97a5yNf0X5d5dWhGZGlpZ4rd/S', '500-80-1115',
        'SOCIAL_SECURITY_NUMBER', 'Romano', 'Tedeschi', '2000-06-29', 1, 1, 0, 2);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (8, 0, '1d40269e-a7af-387c-b2c8-ad13a4c65a4b', 'Liquibase', 'Liquibase', '2022-12-09', '17:38:14', '2022-12-09',
        '17:38:14', 'patient6', '$2a$10$RCPK0YKDf5GuI2U6PwHUUuNRYx9Fc9L2yKEK3DBxhEWTNvJeqlOiK', '500-80-1116',
        'SOCIAL_SECURITY_NUMBER', 'Francesco', 'Carrozzo', '2000-06-29', 1, 1, 0, 2);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (9, 0, 'e1dc8e8b-f137-4ad4-a7ad-45887809c44b', 'Liquibase', 'Liquibase', '2022-12-09', '16:24:36', '2022-12-09',
        '16:24:36', 'drkelso', '$2a$10$gk4WgUsNHcqftvY.l4ENU.K4lckPtuWWbKvdGc1gk7cKv6QlCwl/W', '122-80-1236',
        'SOCIAL_SECURITY_NUMBER', 'Robert', 'Kelso', '1965-06-09', 1, 0, 1, 2);

INSERT INTO patient_reservation.user (id, version_, uid, creator, modifier, created_date, created_time,
                                      last_modified_date, last_modified_time, username, password_hash,
                                      patient_identifier, patient_identifier_type, first_name, last_name, date_of_birth,
                                      login_enabled, is_patient, is_doctor, doctor_id)
VALUES (10, 0, '99447e71-7887-4f73-a04d-62b337be37d0', 'Liquibase', 'Liquibase', '2022-12-09', '17:38:14', '2022-12-09',
        '17:38:14', 'patient7', '$2a$10$RCPK0YKDf5GuI2U6PwHUUuNRYx9Fc9L2yKEK3DBxhEWTNvJeqlOiK', '500-80-1117',
        'SOCIAL_SECURITY_NUMBER', 'Matteo', 'Russo', '2001-03-16', 1, 1, 0, 9);

INSERT INTO patient_reservation.role(id, version_, uid, creator, modifier, created_date, created_time,
                                     last_modified_date, last_modified_time, name)
VALUES (1, 0, 'dfb93c7d-1ee6-4790-8d58-5cb47889c2c6', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'ADMIN');

INSERT INTO patient_reservation.role(id, version_, uid, creator, modifier, created_date, created_time,
                                     last_modified_date, last_modified_time, name)
VALUES (2, 0, 'ae5e08f9-31f1-476e-8533-35f7db67375c', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'WRITER');

INSERT INTO patient_reservation.role(id, version_, uid, creator, modifier, created_date, created_time,
                                     last_modified_date, last_modified_time, name)
VALUES (3, 0, 'f78f1569-30af-4dab-affb-feecf4cdcc62', 'Liquibase', 'Liquibase', '2022-12-08', '16:20:00', '2022-12-08',
        '16:20:00', 'READER');

INSERT INTO patient_reservation.user_role(user_id, role_id)
VALUES (1, 1);

INSERT INTO patient_reservation.user_role(user_id, role_id)
VALUES (2, 2);

INSERT INTO patient_reservation.user_role(user_id, role_id)
VALUES (9, 2);

INSERT INTO patient_reservation.visit (id, version_, uid, creator, modifier, created_date, created_time,
                                       last_modified_date, last_modified_time, date, type, reason, family_history,
                                       doctor_id, patient_id)
VALUES (1, 0, '47a71127-b873-440c-b525-dbce4dc95362', 'Liquibase', 'Liquibase', '2022-12-10', '20:00:00', '2022-12-10',
        '20:00:00', '2022-12-12 10:30:00', 'DOCTOR_OFFICE', 'RECURRING_VISIT', 'periodic lupus visit', 2, 3);

INSERT INTO patient_reservation.visit (id, version_, uid, creator, modifier, created_date, created_time,
                                       last_modified_date, last_modified_time, date, type, reason, family_history,
                                       doctor_id, patient_id)
VALUES (2, 0, '24ef62cc-6b23-4881-b6e1-f826a7911641', 'Liquibase', 'Liquibase', '2022-12-10', '20:00:00', '2022-12-10',
        '20:00:00', '2022-12-15 16:00:00', 'HOME', 'RECURRING_VISIT', null, 2, 4);

INSERT INTO patient_reservation.visit (id, version_, uid, creator, modifier, created_date, created_time,
                                       last_modified_date, last_modified_time, date, type, reason, family_history,
                                       doctor_id, patient_id)
VALUES (3, 0, '0303007e-0fb1-4017-b781-08cf44a86626', 'Liquibase', 'Liquibase', '2022-12-10', '20:00:00', '2022-12-10',
        '20:00:00', '2022-12-23 18:10:00', 'HOME', 'URGENT', null, 2, 5);

INSERT INTO patient_reservation.visit (id, version_, uid, creator, modifier, created_date, created_time,
                                       last_modified_date, last_modified_time, date, type, reason, family_history,
                                       doctor_id, patient_id)
VALUES (4, 0, '129be59f-eac2-4c66-95e2-7be8d709de5c', 'Liquibase', 'Liquibase', '2022-12-10', '20:00:00', '2022-12-10',
        '20:00:00', '2022-12-23 18:10:00', 'HOME', 'RECURRING_VISIT', null, 9, 10);