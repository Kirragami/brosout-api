package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class ForbiddenException extends ApplicationException {
    
    public ForbiddenException(String message) {
        super(ErrorCode.FORBIDDEN, message);
    }
    
    public ForbiddenException(String message, Throwable cause) {
        super(ErrorCode.FORBIDDEN, message, cause);
    }
} 