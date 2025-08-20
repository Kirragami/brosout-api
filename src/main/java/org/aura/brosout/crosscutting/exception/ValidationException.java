package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

import java.util.List;
import java.util.Map;

public class ValidationException extends ApplicationException {
    
    private final Map<String, String> fieldErrors;
    private final List<String> globalErrors;
    
    public ValidationException(String message) {
        super(ErrorCode.VALIDATION_ERROR, message);
        this.fieldErrors = Map.of();
        this.globalErrors = List.of();
    }
    
    public ValidationException(String message, Map<String, String> fieldErrors) {
        super(ErrorCode.VALIDATION_ERROR, message);
        this.fieldErrors = fieldErrors;
        this.globalErrors = List.of();
    }
    
    public ValidationException(String message, List<String> globalErrors) {
        super(ErrorCode.VALIDATION_ERROR, message);
        this.fieldErrors = Map.of();
        this.globalErrors = globalErrors;
    }
    
    public ValidationException(String message, Map<String, String> fieldErrors, List<String> globalErrors) {
        super(ErrorCode.VALIDATION_ERROR, message);
        this.fieldErrors = fieldErrors;
        this.globalErrors = globalErrors;
    }
    
    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
    
    public List<String> getGlobalErrors() {
        return globalErrors;
    }
} 