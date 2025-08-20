package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class AuthenticationException extends ApplicationException {
    
    public AuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
    
    public AuthenticationException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
} 