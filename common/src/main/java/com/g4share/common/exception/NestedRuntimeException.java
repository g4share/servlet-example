//Copyright (c) 2023 g4share

package com.g4share.common.exception;

public abstract class NestedRuntimeException extends RuntimeException {

    public NestedRuntimeException(final String msg) {
        super(msg);
    }

    public NestedRuntimeException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}