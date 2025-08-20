package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class EntityNotFoundException extends BusinessException {
    
    public EntityNotFoundException(String message) {
        super(ErrorCode.ENTITY_NOT_FOUND, message);
    }
    
    public EntityNotFoundException(String message, Throwable cause) {
        super(ErrorCode.ENTITY_NOT_FOUND, message, cause);
    }
} 