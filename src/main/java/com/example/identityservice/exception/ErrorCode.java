package com.example.identityservice.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    VALIDATION_FAILED("EIS00001", "Validation failed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("EIS00002", "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("EIS00003", "Unauthenticated", HttpStatus.UNAUTHORIZED),
    GENERATE_TOKEN_FAILED("EIS00004", "Generate token failed", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_ALREADY_EXISTS("EIS00005", "Username already exists", HttpStatus.CONFLICT),
    UNAUTHORIZED("EIS00006", "Unauthorized", HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND("EIS00007", "Role not found", HttpStatus.NOT_FOUND),
    PERMISSION_NOT_FOUND("EIS00008", "Permission not found", HttpStatus.NOT_FOUND),
    INVALID_KEY("EIS00009", "Uncategorized error", HttpStatus.BAD_REQUEST),
    USERNAME_TOO_SHORT("EIS00010", "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USERNAME_TOO_LONG("EIS00011", "Username must be at most {max} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID("EIS00012", "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    DOB_INVALID("EIS00013", "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXISTS("EIS00014", "User already exists", HttpStatus.CONFLICT),
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
