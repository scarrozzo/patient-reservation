package com.patient.reservation.security;

public class Authorities {
    public static final String ROLE_ADMIN = "hasAuthority('ADMIN')";
    public static final String ROLE_WRITER = "hasAnyAuthority('WRITER','ADMIN')";
}
