package org.aura.brosout.common.responseWrapper;

import java.time.Instant;

public record ResponseWrapper<T>(
        boolean success,
        String message,
        T data, Integer code,
        long timestamp) {

    public ResponseWrapper {
    }

    public ResponseWrapper(T data) {
        this(true, null, data, null, Instant.now().toEpochMilli());
    }

    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper<>(data);
    }

    public static <T> ResponseWrapper<T> success(T data, String message) {
        return new ResponseWrapper<>(true, message, data, null, Instant.now().toEpochMilli());
    }

    public static <T> ResponseWrapper<T> error(String message) {
        return new ResponseWrapper<>(false, message, null, null, Instant.now().toEpochMilli());
    }

    public static <T> ResponseWrapper<T> error(String message, int code) {
        return new ResponseWrapper<>(false, message, null, code, Instant.now().toEpochMilli());
    }
}

