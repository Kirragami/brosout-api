package org.aura.brosout.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    
    // Authentication Errors (401)
    INVALID_CREDENTIALS("AUTH_001", "Invalid username or password", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("AUTH_002", "User not found", HttpStatus.UNAUTHORIZED),
    ACCOUNT_DISABLED("AUTH_003", "Account is disabled", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("AUTH_004", "Authentication token has expired", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("AUTH_005", "Invalid authentication token", HttpStatus.UNAUTHORIZED),
    INSUFFICIENT_PERMISSIONS("AUTH_006", "Insufficient permissions to access this resource", HttpStatus.UNAUTHORIZED),
    
    // Validation Errors (400)
    INVALID_INPUT("VAL_001", "Invalid input provided", HttpStatus.BAD_REQUEST),
    MISSING_REQUIRED_FIELD("VAL_002", "Required field is missing", HttpStatus.BAD_REQUEST),
    INVALID_FORMAT("VAL_003", "Invalid format for the provided data", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_FORMAT("VAL_004", "Invalid email format", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_FORMAT("VAL_005", "Invalid phone number format", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_WEAK("VAL_006", "Password does not meet security requirements", HttpStatus.BAD_REQUEST),
    
    // Business Logic Errors (400/404/409)
    ENTITY_NOT_FOUND("BUS_001", "Requested entity not found", HttpStatus.NOT_FOUND),
    DUPLICATE_RESOURCE("BUS_002", "Resource already exists", HttpStatus.CONFLICT),
    INVALID_REQUEST("BUS_003", "Invalid request", HttpStatus.BAD_REQUEST),
    BUSINESS_RULE_VIOLATION("BUS_004", "Business rule violation", HttpStatus.BAD_REQUEST),
    DUPLICATE_PHONE("BUS_005", "Phone number already exists", HttpStatus.CONFLICT),
    INVALID_USER_TYPE("BUS_007", "Invalid user type", HttpStatus.BAD_REQUEST),
    
    // Security Errors (401/403)
    UNAUTHORIZED("SEC_001", "Unauthorized access", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("SEC_002", "Access forbidden", HttpStatus.FORBIDDEN),
    
    // System Errors (500)
    DATABASE_ERROR("SYS_001", "Database operation failed", HttpStatus.INTERNAL_SERVER_ERROR),
    EXTERNAL_SERVICE_ERROR("SYS_002", "External service call failed", HttpStatus.INTERNAL_SERVER_ERROR),
    CONFIGURATION_ERROR("SYS_003", "Configuration error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNEXPECTED_ERROR("SYS_004", "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    JWT_PROCESSING_ERROR("SYS_005", "JWT token processing failed", HttpStatus.INTERNAL_SERVER_ERROR),
    ENCRYPTION_ERROR("SYS_006", "Encryption/decryption failed", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_STORAGE_ERROR("SYS_007", "File storage operation failed", HttpStatus.INTERNAL_SERVER_ERROR),
    
    // Validation Errors (400)
    VALIDATION_ERROR("VAL_007", "Validation failed", HttpStatus.BAD_REQUEST);
    
    private final String code;
    private final String description;
    private final HttpStatus httpStatus;
    
    public int getHttpStatusCode() {
        return httpStatus.value();
    }
} 