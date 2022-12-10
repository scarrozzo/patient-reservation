package com.patient.reservation.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.patient.core.util.JsonUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    /**
     * CONFLICT
     */
    @ExceptionHandler({EntityExistsException.class, DataIntegrityViolationException.class, ConflictException.class})
    public ResponseEntity<?> conflict(Throwable e, HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.builder()
                .code(ServiceError.E0002.getCode())
                .message(e.getMessage() != null ? e.getMessage() : ServiceError.E0002.getMessage())
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
    * MethodArgumentNotValidException
    * */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ValidationError error = ValidationError.builder()
                .code(ServiceError.V0000.getCode())
                .message(ex.getMessage() != null ? ex.getMessage() : ServiceError.V0000.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        enrichValidationError(error, ex);
        return new ResponseEntity<>(error, error.getStatus());
    }

    /**
     * HttpMessageNotReadableException
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Error error = Error.builder()
                .code(ServiceError.V0000.getCode())
                .message(ex.getMessage() != null ? ex.getMessage() : ServiceError.V0000.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(error, error.getStatus());
    }


    /**
     * Entity not found exception
     */
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> entityNotFoundException(Throwable e, HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.builder()
                .code(ServiceError.E0001.getCode())
                .message(e.getMessage() != null ? e.getMessage() : ServiceError.E0001.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return responseEntity(error);
    }

    /**
     * User not found  exception
     */
    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<?> userNotFoundException(Throwable e, HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.builder()
                .code(ServiceError.E0003.getCode())
                .message(e.getMessage() != null ? e.getMessage() : ServiceError.E0003.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return responseEntity(error);
    }

    /**
     * FORBIDDEN
     */
    @ExceptionHandler({BadCredentialsException.class, AuthorizationServiceException.class, PermissionDeniedException.class})
    public ResponseEntity<?> forbidden(Throwable e, HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.builder()
                .code(ServiceError.E0004.getCode())
                .message(e.getMessage() != null ? e.getMessage() : ServiceError.E0004.getMessage())
                .status(HttpStatus.FORBIDDEN)
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


    private ValidationError enrichValidationError(ValidationError validationError, MethodArgumentNotValidException exception){
        validationError.setMessage(exception.getMessage());
        validationError.setValidationErrorCount(exception.getBindingResult().getErrorCount());
        validationError.setController(exception.getParameter().getMethod() != null ? exception.getParameter().getMethod().getDeclaringClass().getSimpleName() : "N/A");
        validationError.setMethod(exception.getParameter().getMethod() != null ? exception.getParameter().getMethod().getName() : "N/A");

        if (exception.getParameter().getMethod() != null) validationError.setDto(Arrays.stream((exception.getParameter().getMethod().getParameterTypes())).map(Class::getSimpleName).collect(Collectors.joining(",")));

        List<ValidationErrorItem> errors = new ArrayList<>();
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            errors.add(getError(objectError));
        }

        validationError.setValidationErrors(errors);
        return validationError;
    }

    private static ValidationErrorItem getError(ObjectError error) {
        ValidationErrorItem entry = new ValidationErrorItem();

        if (error instanceof FieldError) {
            entry.setField(((FieldError) error).getField());
            entry.setRejected(((FieldError) error).getRejectedValue());
        } else {
            entry.setField(String.join(",", Objects.requireNonNull(error.getCodes())));
            entry.setName(error.getObjectName());
        }

        entry.setMessage(error.getDefaultMessage());
        return entry;
    }

    @FieldNameConstants
    @Getter
    @Setter
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error{
        private HttpStatus status;
        private String code;
        private Object message;
    }

    @Getter
    @Setter
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ValidationError extends Error {

        private String controller;
        private String method;
        private String dto;
        private int validationErrorCount;

        private List<ValidationErrorItem> validationErrors;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ValidationErrorItem {

        private String field;
        private Object rejected;
        private String message;
        private String name;
    }
}
