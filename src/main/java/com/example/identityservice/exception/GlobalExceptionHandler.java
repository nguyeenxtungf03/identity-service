package com.example.identityservice.exception;


import com.example.identityservice.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ApiResponse<Object> response = new ApiResponse<>(null);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorCode(ErrorCode.VALIDATION_FAILED.getCode());
        response.setErrorMessage(ErrorCode.VALIDATION_FAILED.getMessage());

        String mess = String.join(", ", e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());

        response.setMessage(mess);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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

        return new ResponseEntity<>(new ApiResponse<>().setCode(errorCode.getHttpStatus().value()).setMessage(errorCode.getMessage())
                , errorCode.getHttpStatus());
    }
}
