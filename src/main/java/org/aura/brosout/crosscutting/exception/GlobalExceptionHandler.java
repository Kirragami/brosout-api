package org.aura.brosout.crosscutting.exception;

import lombok.extern.slf4j.Slf4j;
import org.aura.brosout.common.enums.ErrorCode;
import org.aura.brosout.common.responseWrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseWrapper<ErrorDetails>> handleApplicationException(ApplicationException ex, WebRequest request) {
        log.error("Application exception occurred: {}", ex.getMessage(), ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ex.getErrorCode().getCode())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ResponseWrapper.error(ex.getMessage(), ex.getHttpStatus()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseWrapper<ErrorDetails>> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        log.error("Authentication exception occurred: {}", ex.getMessage(), ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ex.getErrorCode().getCode())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseWrapper.error(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseWrapper<ErrorDetails>> handleValidationException(ValidationException ex, WebRequest request) {
        log.error("Validation exception occurred: {}", ex.getMessage(), ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ex.getErrorCode().getCode())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .fieldErrors(ex.getFieldErrors())
                .globalErrors(ex.getGlobalErrors())
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseWrapper.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseWrapper<ErrorDetails>> handleBusinessException(BusinessException ex, WebRequest request) {
        log.error("Business exception occurred: {}", ex.getMessage(), ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ex.getErrorCode().getCode())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseWrapper.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ResponseWrapper<ErrorDetails>> handleSystemException(SystemException ex, WebRequest request) {
        log.error("System exception occurred: {}", ex.getMessage(), ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ex.getErrorCode().getCode())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseWrapper.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseWrapper<ErrorDetails>> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        log.error("Bad credentials exception occurred: {}", ex.getMessage(), ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ErrorCode.INVALID_CREDENTIALS.getCode())
                .message("Invalid username or password")
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseWrapper.error("Invalid username or password", HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<ErrorDetails>> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Method argument not valid exception occurred: {}", ex.getMessage(), ex);
        
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ErrorCode.INVALID_INPUT.getCode())
                .message("Validation failed")
                .path(request.getDescription(false))
                .fieldErrors(fieldErrors)
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseWrapper.error("Validation failed", HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<ErrorDetails>> handleGenericException(Exception ex, WebRequest request) {
        log.error("Unexpected exception occurred: {}", ex.getMessage(), ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ErrorCode.UNEXPECTED_ERROR.getCode())
                .message("An unexpected error occurred")
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseWrapper.error("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
} 