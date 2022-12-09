package com.patient.reservation.exception;

public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException(String message){
        super(message);
    }

    public PermissionDeniedException(String message, Throwable throwable){
        super(message, throwable);
    }

}

