//Copyright (c) 2023 g4share

package com.g4share.rest;

import java.util.Optional;

public enum HTTP_METHOD {

    METHOD_DELETE("DELETE", false),
    METHOD_GET("GET", false),
    METHOD_POST("POST", true),
    METHOD_PUT("PUT", true);

    private final String value;
    private final boolean hasBody;

    HTTP_METHOD(String value, boolean hasBody) {
        this.hasBody = hasBody;
        this.value = value;
    }

    public String value() {
        return value;
    }

    public boolean hasBody() {
        return hasBody;
    }

    public static Optional<HTTP_METHOD> from(String method) {
        for (HTTP_METHOD httpMethod : HTTP_METHOD.values()) {
            if (httpMethod.value.equalsIgnoreCase(method)) {
                return Optional.of(httpMethod);
            }
        }
        return Optional.empty();
    }
}