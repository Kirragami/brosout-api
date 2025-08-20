package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class UnauthorizedException extends ApplicationException {
    
    public UnauthorizedException(String message) {
        super(ErrorCode.UNAUTHORIZED, message);
    }
    
    public UnauthorizedException(String message, Throwable cause) {
        super(ErrorCode.UNAUTHORIZED, message, cause);
    }
} 