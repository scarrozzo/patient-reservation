package com.patient.reservation.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.patient.core.util.JsonUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    /**
     * CONFLICT
     */
    @ExceptionHandler({EntityExistsException.class, DataIntegrityViolationException.class, ConflictException.class})
    public ResponseEntity<?> conflict(Throwable e, HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.builder()
                .code(ServiceError.E0004.getCode())
                .message(e.getMessage() != null ? e.getMessage() : ServiceError.E0004.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();
        return responseEntity(error);
    }

    /**
     * BAD_REQUEST
     */
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class, SecurityException.class})
    public ResponseEntity<?> badRequest(Throwable e, HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.builder()
                .code(ServiceError.V0000.getCode())
                .message(e.getMessage() != null ? e.getMessage() : ServiceError.V0000.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return responseEntity(error);
    }

    /**
     * INTERNAL_SERVER_ERROR
     * OTHER
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> allTheRest(Throwable e, HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.builder()
                .code(ServiceError.E0000.getCode())
                .message(e.getMessage() != null ? e.getMessage() : ServiceError.E0000.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return responseEntity(error);
    }

    protected ResponseEntity<String> responseEntity(Error error) {
        String body;
        try {
            body = JsonUtils.getObjectMapper().writeValueAsString(error);
        } catch (JsonProcessingException e) {
            body = "Not serializable error body";
        }

        return new ResponseEntity<>(body, error.getStatus());
    }

    @Getter
    @Setter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    static class Error{
        private HttpStatus status;
        private String code;
        private Object message;
    }
}
