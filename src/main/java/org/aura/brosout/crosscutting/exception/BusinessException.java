package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class BusinessException extends ApplicationException {
    
    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
    
    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
} 