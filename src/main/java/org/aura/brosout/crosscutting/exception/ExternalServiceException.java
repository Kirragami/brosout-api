package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class ExternalServiceException extends ApplicationException {
    
    public ExternalServiceException(String message) {
        super(ErrorCode.EXTERNAL_SERVICE_ERROR, message);
    }
    
    public ExternalServiceException(String message, Throwable cause) {
        super(ErrorCode.EXTERNAL_SERVICE_ERROR, message, cause);
    }
} 