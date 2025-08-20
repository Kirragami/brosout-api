package org.aura.brosout.crosscutting.exception;

import lombok.Getter;
import org.aura.brosout.common.enums.ErrorCode;

@Getter
public abstract class ApplicationException extends RuntimeException {
    
    private final ErrorCode errorCode;
    private final int httpStatus;
    
    protected ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatusCode();
    }
    
    protected ApplicationException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatusCode();
    }
} 