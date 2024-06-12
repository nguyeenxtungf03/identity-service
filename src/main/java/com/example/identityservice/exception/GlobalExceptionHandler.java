package com.example.identityservice.exception;

import java.util.Map;
import java.util.Objects;

import jakarta.validation.ConstraintViolation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.identityservice.dto.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";
    private static final String MAX_ATTRIBUTE = "max";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException e) {

        String enumKey =
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumKey);

            var constrainViolation = e.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);

            attributes = constrainViolation.getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());
        } catch (IllegalArgumentException ignored) {
        }

        String errorMessage =
                Objects.isNull(attributes) ? errorCode.getMessage() : mapAttribute(errorCode.getMessage(), attributes);

        ApiResponse<Object> response = new ApiResponse<>(null);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorCode(errorCode.getCode());
        response.setErrorMessage(errorMessage);
        response.setMessage(errorMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> appExceptionHandler(AppException e) {
        ApiResponse<Object> response = new ApiResponse<>(null);
        response.setCode(e.getErrorCode().getHttpStatus().value());
        response.setErrorCode(e.getErrorCode().getCode());
        response.setErrorMessage(e.getErrorCode().getMessage());
        response.setMessage(e.getErrorCode().getMessage());

        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<Object>> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return new ResponseEntity<>(
                new ApiResponse<>().setCode(errorCode.getHttpStatus().value()).setMessage(errorCode.getMessage()),
                errorCode.getHttpStatus());
    }

    private String mapAttribute(String mess, Map<String, Object> attributes) {
        String minValue = attributes.get(MIN_ATTRIBUTE).toString();
        String maxValue = attributes.get(MAX_ATTRIBUTE).toString();

        return mess.replace("{" + MIN_ATTRIBUTE + "}", minValue).replace("{" + MAX_ATTRIBUTE + "}", maxValue);
    }
}
