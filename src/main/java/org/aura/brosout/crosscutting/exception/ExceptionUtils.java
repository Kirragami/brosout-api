package org.aura.brosout.crosscutting.exception;

import lombok.NoArgsConstructor;
import org.aura.brosout.common.enums.ErrorCode;

import java.util.Map;
@NoArgsConstructor
public final class ExceptionUtils {

    
    // Authentication Exceptions
    public static AuthenticationException invalidCredentials(String message) {
        return new AuthenticationException(ErrorCode.INVALID_CREDENTIALS, message);
    }
    
    public static AuthenticationException userNotFound(String message) {
        return new AuthenticationException(ErrorCode.USER_NOT_FOUND, message);
    }
    
    public static AuthenticationException accountDisabled(String message) {
        return new AuthenticationException(ErrorCode.ACCOUNT_DISABLED, message);
    }
    
    public static AuthenticationException tokenExpired(String message) {
        return new AuthenticationException(ErrorCode.TOKEN_EXPIRED, message);
    }
    
    public static AuthenticationException invalidToken(String message) {
        return new AuthenticationException(ErrorCode.INVALID_TOKEN, message);
    }
    
    // Business Exceptions
    public static EntityNotFoundException entityNotFound(String message) {
        return new EntityNotFoundException(message);
    }
    
    public static DuplicateResourceException duplicateResource(String message) {
        return new DuplicateResourceException(message);
    }
    
    public static InvalidRequestException invalidRequest(String message) {
        return new InvalidRequestException(message);
    }
    
    public static BusinessException businessRuleViolation(String message) {
        return new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION, message);
    }
    
    // Security Exceptions
    public static UnauthorizedException unauthorized(String message) {
        return new UnauthorizedException(message);
    }
    
    public static ForbiddenException forbidden(String message) {
        return new ForbiddenException(message);
    }
    
    // Infrastructure Exceptions
    public static DatabaseException databaseError(String message) {
        return new DatabaseException(message);
    }
    
    public static DatabaseException databaseError(String message, Throwable cause) {
        return new DatabaseException(message, cause);
    }
    
    public static ExternalServiceException externalServiceError(String message) {
        return new ExternalServiceException(message);
    }
    
    public static ExternalServiceException externalServiceError(String message, Throwable cause) {
        return new ExternalServiceException(message, cause);
    }
    
    public static FileStorageException fileStorageError(String message) {
        return new FileStorageException(message);
    }
    
    public static FileStorageException fileStorageError(String message, Throwable cause) {
        return new FileStorageException(message, cause);
    }
    
    public static SystemException configurationError(String message, Throwable cause) {
        return new SystemException(ErrorCode.CONFIGURATION_ERROR, message, cause);
    }
    
    public static SystemException unexpectedError(String message, Throwable cause) {
        return new SystemException(ErrorCode.UNEXPECTED_ERROR, message, cause);
    }
    
    // Validation Exceptions
    public static ValidationException validationError(String message) {
        return new ValidationException(message);
    }
    
    public static ValidationException validationError(String message, Map<String, String> fieldErrors) {
        return new ValidationException(message, fieldErrors);
    }
    
    // Additional utility methods for new error codes
    public static BusinessException duplicatePhone(String message) {
        return new BusinessException(ErrorCode.DUPLICATE_PHONE, message);
    }
    
    public static SystemException jwtProcessingError(String message, Throwable cause) {
        return new SystemException(ErrorCode.JWT_PROCESSING_ERROR, message, cause);
    }
    
    public static SystemException encryptionError(String message, Throwable cause) {
        return new SystemException(ErrorCode.ENCRYPTION_ERROR, message, cause);
    }
} 