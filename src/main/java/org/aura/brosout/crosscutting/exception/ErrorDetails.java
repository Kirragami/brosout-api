package org.aura.brosout.crosscutting.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorDetails {
    
    private LocalDateTime timestamp;
    private String errorCode;
    private String message;
    private String path;
    private Map<String, String> fieldErrors;
    private List<String> globalErrors;
} 