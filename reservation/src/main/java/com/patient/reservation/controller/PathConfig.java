package com.patient.reservation.controller;

public interface PathConfig {

    String BASE_PATH_API = "/api/v1";
    String UID_TEMPLATE = "/{uid}";

    String USERS = BASE_PATH_API + "/users";
    String VISITS = BASE_PATH_API + "/visits";
    String AUTH = BASE_PATH_API + "/auth";
    String PATIENTS = BASE_PATH_API + "/patients";

}
