package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class DuplicateResourceException extends BusinessException {
    
    public DuplicateResourceException(String message) {
        super(ErrorCode.DUPLICATE_RESOURCE, message);
    }
    
    public DuplicateResourceException(String message, Throwable cause) {
        super(ErrorCode.DUPLICATE_RESOURCE, message, cause);
    }
} 