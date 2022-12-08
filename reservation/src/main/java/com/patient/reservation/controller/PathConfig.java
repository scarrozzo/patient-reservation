package com.patient.reservation.controller;

public interface PathConfig {

    String BASE_PATH_API = "/api/v1";
    String USERS = BASE_PATH_API + "/users";
    String VISITS = BASE_PATH_API + "/vists";

    String UID_TEMPLATE = "/{uid}";
}
