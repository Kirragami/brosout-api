package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class DatabaseException extends ApplicationException {
    
    public DatabaseException(String message) {
        super(ErrorCode.DATABASE_ERROR, message);
    }
    
    public DatabaseException(String message, Throwable cause) {
        super(ErrorCode.DATABASE_ERROR, message, cause);
    }
} 