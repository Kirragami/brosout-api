package org.aura.brosout.crosscutting.exception;

import org.aura.brosout.common.enums.ErrorCode;

public class FileStorageException extends ApplicationException {
    
    public FileStorageException(String message) {
        super(ErrorCode.FILE_STORAGE_ERROR, message);
    }
    
    public FileStorageException(String message, Throwable cause) {
        super(ErrorCode.FILE_STORAGE_ERROR, message, cause);
    }
} 