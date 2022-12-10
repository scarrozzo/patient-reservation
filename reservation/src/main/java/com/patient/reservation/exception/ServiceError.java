package com.patient.reservation.exception;

import lombok.Getter;

public enum ServiceError {
    /**
     * Used for validations
     * The message of the validation can be changed using the validation annotation.
     * The validations messages are going to be shown in an array.
     */
    V0000(ValidationErrorCode.V0000, "Validation error"),

    /**
     * Local error codes.
     */
    E0000(ErrorCode.E0000, "Generic error."),
    E0001(ErrorCode.E0001, "Entity not found"),
    E0002(ErrorCode.E0002, "Entity already exists"),
    E0003(ErrorCode.E0003, "User not Found"),
    E0004(ErrorCode.E0004, "Forbidden"),
    E0005(ErrorCode.E0005, "Invalid role for patient"),
    E0006(ErrorCode.E0006, "The patient flag must be true"),
    E0007(ErrorCode.E0007, "The doctor flag must be false"),
    E0008(ErrorCode.E0008, "The user is not a patient"),
    E0009(ErrorCode.E0009, "You are trying to associate the patient with another doctor."),
    E0010(ErrorCode.E0010, "You are trying to modify a patient not associated with you"),
    E0011(ErrorCode.E0011, "Visit date must be greater than or equal to today"),
    E0012(ErrorCode.E0012, "You already have visits in the hour indicated");

    @Getter
    private String code;

    @Getter
    private String message;

    ServiceError(String code, String errorMessage){
        this.code = code;
        this.message = errorMessage;
    }

    class ValidationErrorCode {
        public static final String V0000 = "V0000";
        public static final String V0001 = "V0001";
        public static final String V0002 = "V0002";
    }

    class ErrorCode {
        public static final String E0000 = "E0000";
        public static final String E0001 = "E0001";
        public static final String E0002 = "E0002";
        public static final String E0003 = "E0003";
        public static final String E0004 = "E0004";
        public static final String E0005 = "E0005";
        public static final String E0006 = "E0006";
        public static final String E0007 = "E0007";
        public static final String E0008 = "E0008";
        public static final String E0009 = "E0009";
        public static final String E0010 = "E0010";
        public static final String E0011 = "E0011";
        public static final String E0012 = "E0012";
        public static final String E0013 = "E0013";
        public static final String E0014 = "E0014";
        public static final String E0015 = "E0015";
    }
}
