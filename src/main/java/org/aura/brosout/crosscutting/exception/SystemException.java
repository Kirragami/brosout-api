package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class SystemException extends ApplicationException {
    
    public SystemException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
    
    public SystemException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
} 