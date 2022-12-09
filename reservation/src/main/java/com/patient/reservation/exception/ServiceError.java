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
    E0008(ErrorCode.E0008, "The user is not a patient");

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
    }
}
