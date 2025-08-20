package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class InvalidRequestException extends BusinessException {
    
    public InvalidRequestException(String message) {
        super(ErrorCode.INVALID_REQUEST, message);
    }
    
    public InvalidRequestException(String message, Throwable cause) {
        super(ErrorCode.INVALID_REQUEST, message, cause);
    }
} 