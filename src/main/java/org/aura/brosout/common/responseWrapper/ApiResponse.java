package org.aura.brosout.common.responseWrapper;

import java.time.Instant;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data, Integer code,
        long timestamp) {

    public ApiResponse {
    }

    public ApiResponse(T data) {
        this(true, null, data, null, Instant.now().toEpochMilli());
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, null, Instant.now().toEpochMilli());
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, null, Instant.now().toEpochMilli());
    }

    public static <T> ApiResponse<T> error(String message, int code) {
        return new ApiResponse<>(false, message, null, code, Instant.now().toEpochMilli());
    }
}

